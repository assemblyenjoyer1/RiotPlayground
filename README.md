# League of Legends API Project

This project is a Java application that uses the Riot Games API to retrieve information about League of Legends.

## Installation

To use this application, you'll need to obtain an API key from the Riot Games Developer Portal. Once you have your API key, you'll need to add it to the `application.properties` file located in the `src/main/resources` directory.

riot.api.key=<your-api-key>

After adding your API key, you can build the application using Maven. Run the following command in the root directory of the project:


## Usage

To use the application, simply run the `LeagueOfLegendsApplication` class located in the `src/main/java` directory.

The application will prompt you to enter a summoner name for a player in League of Legends. After entering a valid summoner name, the application will retrieve information about the player, including their rank, champion mastery levels, and match history.

## Dependencies

This project uses the following dependencies:

- Spring Boot
- Spring Web
- Spring Data JPA
- Riot Games API Java Wrapper

## Contributing

If you'd like to contribute to this project, feel free to fork the repository and submit a pull request.
