# Create User with Entitlements Building Block #
A Blackboard building block that allows an administrator to create a user with a specific 
set of entitlements. The primary purpose of this building block is to create a user for an
application based on the REST web services.

# Building #
To build this project, clone the project to a local directory ````cd```` to the directory, then run the following command for unix/osx.
````
./gradlew build
````
or, for windows.
````
gradlew.bat build
````

You can find the building block war file in the ````build/libs```` directory.

# Using the Building Block #
1. Install the Building Block
2. Navigate to System Admin > Tools and Utilities > Create User with Entitlements
3. Give the user a name
4. Give the user's role a name
5. Paste in the list of entitlements you wish to assign
6. Click Submit