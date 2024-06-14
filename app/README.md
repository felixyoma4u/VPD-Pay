# VPD Pay App

This is a simple Android application demonstrating basic fintech features, such as transferring money between accounts and viewing transaction history. It also includes user authentication using Firebase.

## Features

* **Account Management:** Users can view their account balances.
* **Money Transfer:** Users can transfer money between their accounts.
* **Transaction History:** Users can view a history of their past transactions.
* **Data Persistence:** Transaction history is stored locally using Room database.
* **User Authentication:** Users can sign in with existing accounts or register new accounts using Firebase Authentication.

## Technologies Used

* **Kotlin:** The primary programming language for the app.
* **Android SDK:** The framework for building Android applications.
* **Android View:** For building the UI
* **ViewModel:** For managing UI-related data and logic.
* **LiveData:** For observing changes in data and updating the UI accordingly.
* **Room:** For persisting transaction data locally.
* **RecyclerView:** For displaying the transaction history in a list.
* **Coroutines:** For handling asynchronous operations (like database interactions).
* **Firebase Authentication:** For user sign-in and registration.

## Getting Started

1. **Clone the repository:** `git clone https://github.com/felixyoma4u/VPD-Pay.git`
2. **Set up Firebase:** Create a Firebase project and connect it to your Android app. Follow the instructions in the [Firebase documentation](https://firebase.google.com/docs/android/setup).
3. **Open the project in Android Studio.**
4. **Build and run the app on an emulator or physical device.**

## Project Structure

* **`ui`:** Contains the UI components (Activities).
* **`viewModel`:** Contains the ViewModels for managing UI data and logic.
* **`repository`:** Contains the repository classes for interacting with data sources (like the Room database).
* **`database`:** Contains the Room database setup (entities, DAOs, database class).
* **`model`:** Contains data classes representing the app's data (e.g., Account, Transaction).

## Authentication

The app uses Firebase Authentication to handle user sign-in and registration. Users can:

* **Sign in with an existing account:** If they have already registered, they can sign in using their email and password.
* **Register a new account:** If they don't have an account, they can create one by providing their email and password.

The authentication flow is integrated into the app's UI, ensuring that users are authenticated before accessing sensitive features like money transfers.

## Testing

The project includes unit tests for the `TransferViewModel` and potentially other components. To run the tests:

1. Open Android Studio and navigate to the test directory.
2. Right-click on a test class or method and select "Run".

## Future Improvements

* **User Authentication:** Implement user login and registration.
* **Security:** Enhance security measures for handling sensitive data.
* **Additional Features:** Add more fintech features like bill payments, budgeting, etc.
* **UI/UX Enhancements:** Improve the user interface and overall user experience.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.

## License

This project is licensed under the [Me](LICENSE).