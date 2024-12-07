JAVAC = javac
SRC_DIR = src
BUILD_DIR = build

# Find all java files recursively
SOURCES := $(shell find $(SRC_DIR) -name "*.java")

# Convert source files to class files in build directory
CLASSES := $(SOURCES:$(SRC_DIR)/%.java=$(BUILD_DIR)/%.class)

# Main class
MAIN_CLASS = Monopoly

# Default target
all: $(BUILD_DIR) $(CLASSES)

# Create build directory and copy assets
$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)
	mkdir -p $(BUILD_DIR)/codemodel
	mkdir -p $(BUILD_DIR)/view
	cp -r $(SRC_DIR)/view/assets $(BUILD_DIR)/view/

# Compile java files
$(BUILD_DIR)/%.class: $(SRC_DIR)/%.java
	$(JAVAC) -d $(BUILD_DIR) -cp $(SRC_DIR) $<

# Run the application
run: all
	java -cp $(BUILD_DIR) $(MAIN_CLASS)

# Clean build files
clean:
	rm -rf $(BUILD_DIR)

.PHONY: all run clean
