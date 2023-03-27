package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.Tlhelp32.PROCESSENTRY32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

public class ProcessUtils {

    private static String cmd(String gamename) throws IOException {
        String commandline = "";
        List<HWND> windows = getWindowsForProcess(getProcessId(gamename));
        if (!windows.isEmpty()) {
            commandline = getCommandLineForWindow(windows.get(0)).orElse("");
        }

        return commandline;
    }

    private static int getProcessId(String gamename) {
        HANDLE snapshot = Kernel32.INSTANCE.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinNT.DWORD(0));
        try {
            PROCESSENTRY32.ByReference entry = new PROCESSENTRY32.ByReference();
            while (Kernel32.INSTANCE.Process32Next(snapshot, entry)) {
                String processName = Native.toString(entry.szExeFile).trim();
                if (processName.equalsIgnoreCase(gamename)) {
                    return entry.th32ProcessID.intValue();
                }
            }
        } finally {
            Kernel32.INSTANCE.CloseHandle(snapshot);
        }
        return 0;
    }

    private static List<HWND> getWindowsForProcess(int processId) {
        List<HWND> windows = new ArrayList<>();
        User32.INSTANCE.EnumWindows((hWnd, data) -> {
            IntByReference processIdRef = new IntByReference();
            User32.INSTANCE.GetWindowThreadProcessId(hWnd, processIdRef);
            if (processIdRef.getValue() == processId) {
                windows.add(hWnd);
            }
            return true;
        }, null);
        return windows;
    }

    private static Optional<String> getCommandLineForWindow(HWND hWnd) throws IOException {
        char[] buffer = new char[2048];
        User32.INSTANCE.GetWindowText(hWnd, buffer, buffer.length);
        String windowText = Native.toString(buffer).trim();
        System.out.println(windowText);
        if (!windowText.isEmpty()) {
            int processId = getProcessIdFromWindowTitle(windowText);
            if (processId > 0) {
                ProcessBuilder builder = new ProcessBuilder("jps", "-l");
                builder.redirectErrorStream(true);
                Process process = builder.start();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains(String.valueOf(processId))) {
                            String commandLine = line.substring(line.indexOf(' ') + 1);
                            return Optional.of(commandLine);
                        }
                    }
                }
            }
        }
        return Optional.empty();
    }

    private static int getProcessIdFromWindowTitle(String title) {
        String[] parts = title.split("-");
        if (parts.length > 1) {
            String processIdStr = parts[parts.length - 1].trim();
            try {
                return Integer.parseInt(processIdStr);
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return 0;
    }

    private static String findString(String text, String from, String to) {
        int pFrom = text.indexOf(from) + from.length();
        int pTo = text.lastIndexOf(to);
        if (pFrom < from.length() || pTo < 0 || pTo < pFrom) {
            return "";
        }
        return text.substring(pFrom, pTo);
    }

    public static void main(String[] args) throws IOException {
        String gamename = "LeagueClientUx.exe";
        String commandline = cmd(gamename);
        System.out.println("Command line: " + commandline);
        String port = findString(commandline, "--app-port=", "\" \"--");
        System.out.println("Port: " + port);
    }
}