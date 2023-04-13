package org.example.DTO.test;

public class test {
    /*
    private static final String LEAGUE_CLIENT_UX = "LeagueClientUx.exe";

    private static String getCommandLine(String processName) throws IOException {
        ProcessBuilder powerShellProcessBuilder = new ProcessBuilder("powershell.exe",
                "Get-WmiObject Win32_Process | Where-Object { $_.Name -eq '" + processName + "' } | ForEach-Object { $_.CommandLine }");
        Process powerShellProcess = powerShellProcessBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(powerShellProcess.getInputStream()));
        String line;
        String output = "";
        while ((line = reader.readLine()) != null) {
            output += line;
        }

        return output;
    }

    private static String findString(String input, String start, String end) {
        int startIndex = input.indexOf(start);
        if (startIndex == -1) {
            return null; // start string not found in input
        }
        int endIndex = input.indexOf(end, startIndex + start.length());
        if (endIndex == -1) {
            return null; // end string not found in input
        }
        if (endIndex <= startIndex) {
            return null; // end string found before start string or at same position as start string
        }
        return input.substring(startIndex + start.length(), endIndex);
    }

    private static String getAuthToken() throws IOException {
        String commandLine = getCommandLine(LEAGUE_CLIENT_UX);
        return findString(commandLine, "--remoting-auth-token=", "\" \"--locale=");
    }

    private static String getRiotClientAppPort() throws IOException {
        String commandLine = getCommandLine(LEAGUE_CLIENT_UX);
        return findString(commandLine, "--riotclient-app-port=", "\" \"--no-rads");
    }

    private static String getRemotingAuthToken() throws IOException {
        String commandLine = getCommandLine("LeagueClient.exe");
        return findString(commandLine, "--remoting-auth-token=", "\" \"--locale=");
    }

    private static String getAppPort() throws IOException {
        String commandLine = getCommandLine("LeagueClient.exe");
        return findString(commandLine, "--app-port=", "\" \"--install");
    }

    private static String makeRequest(String type, String url, boolean client) throws IOException {
        int port;
        String token;

        if (client) {
            port = Integer.parseInt(getAppPort());
            token = Base64.getEncoder().encodeToString(("riot:" + getRemotingAuthToken()).getBytes(StandardCharsets.ISO_8859_1));
        } else {
            port = Integer.parseInt(getRiotClientAppPort());
            token = Base64.getEncoder().encodeToString(("riot:" + getAuthToken()).getBytes(StandardCharsets.ISO_8859_1));
        }

        URL obj = new URL("https://127.0.0.1:" + port + url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(type);
        con.setRequestProperty("Authorization", "Basic " + token);
        con.setRequestProperty("Content-Type", "application/json");

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public static void main(String[] args) throws IOException {
        String response = makeRequest("GET", "/chat/v5/participants/champ-select", false);
        System.out.println(response);
    }

     */
}
