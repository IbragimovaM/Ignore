# Ignore

This application allows you to list files not ignored by `.ignore` file

# .ignore file format
    # a comment
    example/path/to/directory/
    example/path/to/file.txt

## Build
To build, execute `build` and `jar` Gradle tasks.
The JAR will be located at `build/libs/list.jar`.
## Usage
`java -jar list.jar`
