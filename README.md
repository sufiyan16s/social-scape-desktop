📱 Social Scape - Social Media Post Planner (Desktop Edition)
A powerful desktop application built with JavaFX, MySQL, and FXML, designed to help users schedule, manage, and analyze social media posts with built-in media previews and analytics.

🚀 Features
✅ Core Features
- 🔐 User Authentication (Login/Register with SHA-256 hashing)
- 👥 Role-Based Access (Admin / User)
- 📝 Post Management (Create, Edit, Delete posts)
- 🖼️ Media Upload (Image/Video preview before saving)
- 📅 Schedule Posts (Date & time picker)
- 🔍 Search & Filter (Keyword + Date filter)
- 📊 Analytics (Bar Chart) (Post count per platform or date)
- 📦 Export to CSV (One-click export of posts)
- ☁️ Database: MySQL + optional SQLite (Offline support planned)

💡 Bonus Features (Phase 2)
- 🎨 Dark/Light Mode Toggle
- 🧭 Calendar View (Highlight post days)
- 💾 Backup & Restore (via JSON/SQL export)
- 🚪 Logout button + scene switch
- 📉 Analytics Dashboard using JavaFX Charts
- 🔁 Context Menus for table post editing
- 🔍 Live Search Filter on title/platform/caption
- 🔄 Scene Switching (Login → Dashboard → Logout)

🧱 Tech Stack
| Layer | Tools Used | 
| Language | Java 17+ | 
| GUI | JavaFX + FXML | 
| Database | MySQL (JDBC) | 
| Media | JavaFX ImageView / MediaView | 
| Charts | JavaFX BarChart | 
| Export | Java IO + CSV | 
| Auth | SHA-256 encryption (custom utility) | 
| Optional | SQLite (offline mode) | 



🗂️ Folder Structure
The repository is structured as follows:
socialscape-desktop/
- │── src/main/java/com/sufiyan/socialscape/
- │   ├── app/
- │   │   ├── MainApp.java
- │   ├── controller/
- │   │   ├── DashboardController.java
- │   │   ├── LoginController.java
- │   │   ├── RegisterController.java
- │   ├── dao/
- │   │   ├── DBConnection.java
- │   │   ├── PostDAO.java
- │   │   ├── UserDAO.java
- │   ├── model/
- │   │   ├── Post.java
- │   │   ├── User.java
- │   ├── utils/
- │   │   ├── HashUtil.java
- │   │   ├── TestDB.java
- │── src/resources/
- │   ├── css/
- │   │   ├── style.css
- │   ├── media/
- │   │   ├── IMG_20240429_174824.jpg
- │   │   ├── media_1748193622955.jpg
- │   │   ├── media_1748194833181.jpg
- │   │   ├── media_1748285979812.jpg
- │   ├── sql/
- │   ├── view/
- │   │   ├── dashboard.fxml
- │   │   ├── login.fxml
- |   |   ├── register.fxml

This structure ensures clear separation of concerns, making the project modular and maintainable.

⚙️ Setup Instructions
- 📦 Clone the Repository
git clone https://github.com/yourusername/socialscape-desktop.git
cd socialscape-desktop
- 🛠️ Set Up MySQL Database
CREATE DATABASE socialscape;
USE socialscape;
-- Run schema.sql to initialize tables
- 🗂️ Add MySQL Connector JAR to your IDE classpath.
- 💻 Run the application
- Execute MainApp.java (set JavaFX VM options if required).
- 🧪 Test Functionality
- Register a new user
- Login and add posts with images/videos
- Search, filter, and export data

🛡️ Security Notes
- Passwords are hashed using SHA-256 before storing in the database.
- Database inputs are sanitized using PreparedStatements.

🧑‍💻 Authors
👨‍💻 Md Sufiyan (Developer & Designer)

🤝 Contribution Guidelines
You can contribute by:
- Forking the repository
- Submitting pull requests with enhancements

📃 License
This project is licensed under the MIT License. Feel free to use it for educational and academic purposes.

🔮 Future Enhancements
- 🔌 Plugin system for API integration (YouTube, Instagram)
- ☁️ Firebase-based cross-platform sync
- 📱 Android & Desktop unified experience

This enhanced structure should improve clarity and make collaboration easier. Let me know if you'd like further modifications! 🚀
