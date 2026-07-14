# 🎬 Base Architecture Sample
A modern Android application demonstrating Clean Architecture, Jetpack Compose, and Reactive programming using the OMDb API.

---

## Build Configuration & Security
The project uses a dynamic build configuration in `app/build.gradle.kts` to keep the environment secure and flexible:
- **Property Injection**: It automatically reads `local.properties` and injects values into the app via `BuildConfig` (`BUILD_API_KEY`, `BUILD_BASE_URL`).
- **Dynamic Package Naming**: The `namespace` and `applicationId` are generated dynamically using a `packageSuffix` defined in `gradle.properties`, allowing for easy rebranding or multi-environment setups.
- **Feature Flags**: It enables `buildConfig` and `compose` features explicitly, keeping the build artifact optimized.

---

## 🔑 API Configuration
This project utilizes the **OMDb API** (Open Movie Database) for retrieving film information and artwork.

- **API Documentation**: [omdbapi.com](https://www.omdbapi.com/)
- **Request an API Key**: [omdbapi.com/apikey.aspx](https://www.omdbapi.com/apikey.aspx)

### Local Environment Setup
For security, the API key is managed via `local.properties`. To run the project, add the following lines to your `local.properties` file located in the root directory:

```properties
OMDB_API_KEY="your_api_key_here"
BASE_URL="https://www.omdbapi.com/"
```

## 🏗️ Architecture: Data Layer
The Data Layer is divided into two modules following the **Repository Pattern** and the **Data Source Pattern**. It is responsible for fetching, persisting, and mapping data from external sources into Domain models.

### 🌐 Remote Data Layer (`data_remote`)
Handles all network communication with the OMDb API.

- **Technology**: [Retrofit](https://square.github.io/retrofit/) with [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html).
- **BaseResponse**: A sealed class wrapper that standardizes API responses into `Success`, `SuccessNotFound`, and `Error` states.
- **BaseApiHandler**: Provides the `safeApiRequest` helper, which executes suspend functions and automatically maps DTOs to Domain models while handling exceptions.
- **Repository Implementation**: `FilmRepositoryImpl` implements the Domain layer interface, ensuring the rest of the app remains agnostic of the network implementation.

### 💾 Local Data Layer (`data_local`)
Handles local persistence using a reactive database.

- **Technology**: [Room Database](https://developer.android.com/training/data-storage/room).
- **Reactivity**: Uses `Flow` to provide real-time updates from the database to the UI.
- **BaseLocalHandler**: A utility class that manages safe database executions through:
    - `safeLocalCall`: For one-shot operations (Insert, Delete).
    - `safeLocalFlow`: For reactive data streams, handling mapping from Entities to Domain models and managing database exceptions.
- **Data Source Implementation**: `FilmDataSourceImpl` manages the favorites library, including searching, sorting (ASC/DESC), and existence checks.

---

## 🧠 Architecture: Domain Layer
The Domain Layer is the central part of the application, completely independent of UI, Frameworks, or Data sources. It uses **Pure Kotlin** to ensure business logic remains testable and reusable.

### 📝 Domain Models
- **Technology**: Pure Kotlin Data Classes.
- **Role**: Represents the actual business data (e.g., `FilmModel`). These models are agnostic of JSON structure or Database schemas.

### ⚙️ Use Cases (`BaseUseCase`)
The project uses a command-pattern approach for business logic:
- **BaseUseCase<Params, DOMAIN>**: A base class that standardizes execution. It uses the `invoke` operator to trigger logic and automatically maps any low-level exceptions (Network/DB) into **DomainExceptions** using the `toDomainError()` extension.
- **Single Responsibility**: Each Use Case handles exactly one action, such as `GetFilmByTitleUseCase` or `SaveFavoriteFilmUseCase`.

### 🔌 Dependency Inversion (Interfaces)
- **Repositories & Data Sources**: The Domain layer defines **Interfaces** (e.g., `FilmDataSource`). The implementations are provided by the Data Layer, but the Domain layer remains decoupled, allowing for easier testing and future changes to the data source.

---

## 🧠 Architecture: Presentation Layer
The Presentation Layer follows the MVVM Pattern combined with Unidirectional Data Flow (UDF) using Jetpack Compose.

### 🛠️ Core Infrastructure (BaseViewModel & UiState)
The UI logic is standardized through a robust base architecture:
- **UiState**`<T>`: A sealed class that represents the state of a screen: `Idle`, `Loading`, `Success(T)`, `SuccessEmpty`, and `Error(DomainException)`.
- **BaseViewModel**: Encapsulates common UI logic, providing helpers like:
  - `executeUseCase`: Manages the lifecycle of a remote/suspend call, automatically updating `UiState`.
  - `collectFlow`: Safely collects reactive streams from the Domain/Data layer and maps them to UI models.
  - `executeLocalCall`: Handles fast, silent local database operations without forcing a loading state on the entire UI.

### 🧱 Jetpack Compose Pattern (Stateful vs. Stateless)
Screens are divided to maximize testability and reusability:

- **Stateful Screen**: Injects the ViewModel via Koin, collects states using `collectAsStateWithLifecycle`, and handles navigation callbacks.
- **Stateless Content**: A pure UI component that receives data as parameters and "bubbles up" user events via lambdas (callbacks).
- **Components**: Reusable "Lego blocks" (e.g., `FilmRatingCard`, `MetadataChip`) that ensure visual consistency across the app.

### 🗺️ Navigation & SAA
The project follows **Single Activity Architecture (SAA)**:
- **Jetpack Navigation**: Uses the modern Kotlin DSL for navigation.
- **Type-Safety**: Routes and arguments are defined using Kotlin Serialization, eliminating the need for String-keys and SafeArgs plugins.
- **Intent Pattern**: Features define their navigation "Intent" through Interfaces (e.g., `HomeNavigator`), decoupling the UI from the underlying Navigation library.

---

## 🧪 Tech Stack Summary
- **UI**: Jetpack Compose (Material 3).
- **DI**: Koin.
- **Async**: Kotlin Coroutines & Flow.
- **Network**: Retrofit & OkHttp.
- **Database**: Room.
- **Image Loading**: Coil.
- **Serialization**: Kotlinx Serialization.