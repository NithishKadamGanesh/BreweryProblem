
# Brewery Management Application

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [File Structure](#file-structure)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Usage](#usage)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Overview

The **Brewery Management Application** is designed to automate and streamline the various operations within a brewery, from managing ingredient inventories to producing beverage batches based on predefined recipes. The system includes a user-friendly interface that allows users to interact with the application seamlessly.

## Features

- **Ingredient Management**: Add, update, and track ingredients required for brewing.
- **Recipe Library**: Store and manage recipes used in beverage production.
- **Batch Brewing**: Automate the production of beverage batches based on recipes.
- **Inventory Management**: Keep track of ingredient stock levels and container availability.
- **Production System**: Manage and monitor the entire production process, ensuring efficient workflow.
- **User Interface**: Accessible via a command-line or graphical user interface (GUI).

## File Structure

Below is a list of key Java classes that implement the core features of the application:

- `Ingredient.java`: Defines the attributes and methods for managing ingredients such as name, quantity, and unit.
- `Recipe.java`: Represents a recipe, linking multiple ingredients with their respective quantities.
- `BeverageBatch.java`: Handles the production of beverage batches based on recipes.
- `Container.java`: Manages storage containers used for storing beverage batches.
- `Inventory.java`: Tracks the availability of ingredients and containers.
- `RecipeLibrary.java`: Manages the storage and retrieval of recipes.
- `ProductionSystem.java`: Coordinates the brewing process and inventory updates.
- `BreweryController.java`: Implements the business logic, connecting different components of the application.
- `BreweryView.java`: Provides the user interface for interacting with the system.
- `BreweryApp.java`: Entry point for running the application.
