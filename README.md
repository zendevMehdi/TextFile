# TextFile

Simple tools to read, write, append, etc. You can do it easily

## Authors

- [@Mehdi Lavasani](https://github.com/zendevMehdi)


## Features

- Read from text file
- Write to the text file
- Append new text to the end of file
- Search text in file
- Replace new word in the file
- clear entire file
  
## Installation

You can get jar from release section or create new project and add src folder to the project.


## Usage/Examples

I created simple text file and read, write from the file

```java
package org.zendev.lib.text;

import org.zendev.lib.text.options.CountOption;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        var tf = new TextFile("build.txt");

        System.out.println(tf.read());
        System.out.println(tf.search("sample", true, true));

        System.out.printf("Characters: %d\n", tf.count(CountOption.CHARACTERS));
        System.out.printf("Empty lines: %d\n", tf.count(CountOption.EMPTY_LINES));
    }
}
```

