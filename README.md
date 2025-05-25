# Social-Scape-Desktop
JavaFX-based desktop app for social media planning and scheduling

# Social Scape - Desktop Version

**Social Scape** is a powerful JavaFX-based desktop application that helps social media creator and managers plan, schedual, and traack their content with featuers like media prviews, database storage, notification, and CSV export.

------

## Featuers 

-🔐 User Registration & Login
-📝 Add, Edit, Delete Posts
- 🖼 Upload Media (Images/Videos)
- 📅 Date & Time Scheduling
- 🔎 Search & Filter Posts
- 📤 Export to CSV
- 🖥 Built with JavaFX + MySQL (or SQLite)

-----

##Project Structure

  socialscape
  |----src/
  |   |main/
  |      |---java/
  |      |   |---com.sufiyan.socialsocial
  |      |   |  |---app
  |      |   |  | |---MainApp.java
  |      |   |  |---controller
  |      |   |  |  |---DashboardController.java
  |      |   |  |  |---LoginController.java
  |      |   |  |  |---RegisterController.java
  |      |   |  |---dao
  |      |   |  |  |---DBConnection.java
  |      |   |  |  |---PostDAO.java
  |      |   |  |  |---UserDAO.java
  |      |   |  |---model
  |      |   |  |  |---Post.java
  |      |   |  |  |---User.java
  |      |   |  |---utils
  |      |   |  |  |---Hashutil.java
  |      |   |  |---TestDB.java
  |      |---resourcrs   
  |      |   |---css
  |      |   |  |---style.css
  |      |   |---media 
  |      |   |  |---img1.jpg
  |      |   |  |---img2.jpg
  |      |   |---sql
  |      |   |  |---schema.sql
  |      |   |---view
  |      |   |  |---</>dashboaard.fxml
  |      |   |  |---</>login.fxml
  |      |   |  |---</>register.fxml

  ## Technologies used
  - Java 17+
  - JavaFX 21+
  - MySQL or SQLite
  - JDBC
  - SceneBuilder (for UI design)
 
  ----
  ## Setup Instructions
  ###Prerequisites
  -Java JDK 17 or 21
  -JavaFX SDK installed and running
  Intellij IDEA

  ### Setup Steps
  1. Clone the repo
  2. Import into intellij and mark resources/ as Resources Root
  3. Configure VM options for JavaFX
  4. Create the MySQL database using :-- schema.sql
  5. Run MainApp.java
