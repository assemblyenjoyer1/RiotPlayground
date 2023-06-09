package org.example.DTO.test;

import org.example.DTO.models.enums.Region;

public class Transformer {

    public static String getChampionNameById(long championId) {
        return switch ((int) championId) {
            case 266 -> "Aatrox";
            case 517 -> "Sylas";
            case 412 -> "Thresh";
            case 23 -> "Tryndamere";
            case 79 -> "Gragas";
            case 69 -> "Cassiopeia";
            case 136 -> "Aurelion Sol";
            case 13 -> "Ryze";
            case 523 -> "Aphelios";
            case 78 -> "Poppy";
            case 14 -> "Sion";
            case 497 -> "Rakan";
            case 1 -> "Annie";
            case 498 -> "Xayah";
            case 202 -> "Jhin";
            case 43 -> "Karma";
            case 111 -> "Nautilus";
            case 240 -> "Kled";
            case 99 -> "Lux";
            case 103 -> "Ahri";
            case 2 -> "Olaf";
            case 112 -> "Viktor";
            case 34 -> "Anivia";
            case 27 -> "Singed";
            case 86 -> "Garen";
            case 127 -> "Lissandra";
            case 57 -> "Maokai";
            case 25 -> "Morgana";
            case 28 -> "Evelynn";
            case 105 -> "Fizz";
            case 360 -> "Samira";
            case 74 -> "Heimerdinger";
            case 238 -> "Zed";
            case 68 -> "Rumble";
            case 82 -> "Mordekaiser";
            case 37 -> "Sona";
            case 350 -> "Yuumi";
            case 164 -> "Camille";
            case 96 -> "Kog'Maw";
            case 55 -> "Katarina";
            case 117 -> "Lulu";
            case 22 -> "Ashe";
            case 30 -> "Karthus";
            case 12 -> "Alistar";
            case 122 -> "Darius";
            case 67 -> "Vayne";
            case 110 -> "Varus";
            case 77 -> "Udyr";
            case 89 -> "Leona";
            case 126 -> "Jayce";
            case 134 -> "Syndra";
            case 80 -> "Pantheon";
            case 92 -> "Riven";
            case 121 -> "Kha'Zix";
            case 42 -> "Corki";
            case 268 -> "Azir";
            case 51 -> "Caitlyn";
            case 76 -> "Nidalee";
            case 85 -> "Kennen";
            case 3 -> "Galio";
            case 45 -> "Veigar";
            case 432 -> "Bard";
            case 150 -> "Gnar";
            case 90 -> "Malzahar";
            case 104 -> "Graves";
            case 254 -> "Vi";
            case 10 -> "Kayle";
            case 39 -> "Irelia";
            case 64 -> "Lee Sin";
            case 420 -> "Illaoi";
            case 60 -> "Elise";
            case 235 -> "Senna";
            case 777 -> "Yone";
            case 106 -> "Volibear";
            case 20 -> "Nunu";
            case 4 -> "Twisted Fate";
            case 24 -> "Jax";
            case 102 -> "Shyvana";
            case 429 -> "Kalista";
            case 36 -> "Dr. Mundo";
            case 427 -> "Ivern";
            case 131 -> "Diana";
            case 223 -> "Tahm Kench";
            case 63 -> "Brand";
            case 113 -> "Sejuani";
            case 8 -> "Vladimir";
            case 154 -> "Zac";
            case 421 -> "Rek'Sai";
            case 133 -> "Quinn";
            case 84 -> "Akali";
            case 163 -> "Taliyah";
            case 18 -> "Tristana";
            case 120 -> "Hecarim";
            case 15 -> "Sivir";
            case 246 -> "Qiyana";
            case 236 -> "Lucian";
            case 107 -> "Rengar";
            case 19 -> "Warwick";
            case 72 -> "Skarner";
            case 54 -> "Malphite";
            case 157 -> "Yasuo";
            case 101 -> "Xerath";
            case 17 -> "Teemo";
            case 75 -> "Nasus";
            case 58 -> "Renekton";
            case 119 -> "Draven";
            case 35 -> "Shaco";
            case 50 -> "Swain";
            case 91 -> "Talon";
            case 40 -> "Janna";
            case 115 -> "Ziggs";
            case 245 -> "Ekko";
            case 61 -> "Orianna";
            case 221 -> "Zeri";
            case 114 -> "Fiora";
            case 9 -> "Fiddlesticks";
            case 31 -> "Cho'Gath";
            case 33 -> "Rammus";
            case 7 -> "LeBlanc";
            case 16 -> "Soraka";
            case 26 -> "Zilean";
            case 56 -> "Nocturne";
            case 222 -> "Jinx";
            case 83 -> "Yorick";
            case 888 -> "Renata";
            case 6 -> "Urgot";
            case 145 -> "Kai'sa";
            case 203 -> "Kindred";
            case 21 -> "Miss Fortune";
            case 62 -> "Wukong";
            case 53 -> "Blitzcrank";
            case 98 -> "Shen";
            case 201 -> "Braum";
            case 895 -> "Nilah";
            case 5 -> "Xin Zhao";
            case 29 -> "Twitch";
            case 11 -> "Master Yi";
            case 234 -> "Viego";
            case 711 -> "Vex";
            case 147 -> "Seraphine";
            case 141 -> "Kayn";
            case 887 -> "Gwen";
            case 44 -> "Taric";
            case 32 -> "Amumu";
            case 41 -> "Gangplank";
            case 48 -> "Trundle";
            case 38 -> "Kassadin";
            case 166 -> "Akshan";
            case 161 -> "Vel'Koz";
            case 143 -> "Zyra";
            case 267 -> "Nami";
            case 59 -> "Jarvan IV";
            case 81 -> "Ezreal";
            case 902 -> "Milio";
            case 526 -> "Rell";
            default -> "champion not found" + championId;
        };
    }

    public static Region getRegionByString(String region){
        return switch(region.toLowerCase()){
            case "euw" -> Region.EUW1;
            case "eune" -> Region.EUN1;
            case "brazil" -> Region.BR1;
            case "japan" -> Region.JP1;
            case "korea" -> Region.KR;
            case "la" -> Region.LA1;
            case "la2" -> Region.LA2;
            case "na" -> Region.NA1;
            case "oc" -> Region.OC1;
            case "ph2" -> Region.PH2;
            default -> null;
        };
    }

    public static String getRegionString(String region){
        return switch(region.toLowerCase()){
            case "euw1" -> "EUW";
            case "eun1" -> "EUNE";
            case "br1" -> "BR";
            case "jp1" -> "JP";
            case "kr" -> "KR";
            case "la1" -> "LA1";
            case "la2" -> "LA2";
            case "na1" -> "NA";
            case "oc1" -> "OC1";
            case "ph2" -> "PH2";
            default -> null;
        };
    }
    
    public static String getQueueTypeByQueueID(int id){
        return switch (id){
            case 420 -> "RankedSolo";
            case 440 -> "Ranked Flex";
            case 1100 -> "Ranked TFT";
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }

}
