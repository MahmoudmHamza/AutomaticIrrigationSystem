## Table of Contents

- [Automatic Irrigation System API](#automatic-irrigation-system-api)
  - [Technology Stack](#technology-stack)
  - [Setup Instructions](#setup-instructions)
  - [Endpoints](#endpoints)
    - [Add new plot of land](#add-new-plot-of-land)
    - [Get all plots](#get-all-plots)
    - [Get a specific plot](#get-a-specific-plot)
    - [Configure a plot](#configure-a-plot)
    - [Edit a plot](#edit-a-plot)
    - [Irrigate a plot](#irrigate-a-plot)

# Automatic Irrigation System API

The Automatic Irrigation System API is a RESTful web service developed using the Spring Framework 2.7. It provides endpoints for managing and controlling an automatic irrigation system for agricultural lands. The API allows users to perform the following actions:

1. Add a new plot of land: Users can add a new plot of land by providing the necessary details such as name, area, and sensor ID.
2. Configure a plot of land: Users can configure the irrigation settings for a specific plot of land, including the minimum and maximum moisture levels, irrigation duration, and timeslots.
3. Edit a plot of land: Users can update the details of an existing plot, including its name, area, and sensor ID.
4. List all plots and their details: Users can retrieve a list of all plots along with their respective details, such as name, area, sensor ID, and current irrigation status.

Additionally, the web service integrates with a sensor device to trigger irrigation based on the configured settings. Once a plot needs to be irrigated, the status of the irrigation timeslot is updated upon successful communication with the sensor device. The web service also handles retrying the calls to the sensor device in case it is not available.

The API is developed using Spring Framework's RESTful conventions and utilizes the PostgreSQL database for data storage. Flyway migration is employed for managing database schema changes.

## Technology Stack

- Spring Framework 2.7
- Database: PostgreSQL
- Flyway Migration

## Setup Instructions

1. Clone the repository.
2. Update the database credentials in the application.properties file located in the src/main/resources directory.
3. Run the application using your preferred IDE or build tools.

> Note: Before running the application, make sure to update the database credentials in the application.properties file to match your local database configuration.

## Endpoints

### Add new plot of land

- Endpoint: `POST /plots`
- Description: Creates a new plot of land.

### Get all plots

- Endpoint: `GET /plots`
- Description: Retrieves a list of all plots of land.

### Get a specific plot

- Endpoint: `GET /plots/{plotId}`
- Description: Retrieves a specific plot of land by its ID.

### Configure a plot

- Endpoint: `POST /plots/{plotId}/configure`
- Description: Configures the irrigation settings for a specific plot of land by adding the amount of water needed for the plot as well as assigning the pre-configured irrigation slot and sensor.

### Edit a plot

- Endpoint: `PUT /plots/{plotId}`
- Description: Edits the configurations of a specific plot of land.

### Irrigate a plot

- Endpoint: `POST /plots/{plotId}/irrigate`
- Description: Initiates the irrigation process for a specific plot of land.
