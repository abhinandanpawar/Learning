# 16 - GUI Development with Swing and JavaFX

Java provides several toolkits for creating Graphical User Interfaces (GUIs). This chapter will introduce you to the evolution of GUI development in Java, from the original AWT to the more modern Swing and JavaFX toolkits.

## AWT (Abstract Window Toolkit)
AWT was the first GUI toolkit in Java. It provides a set of basic components for building GUIs, but it is platform-dependent, which means that the look and feel of an AWT application can vary across different operating systems.

Here is a simple example of an AWT application:
```java
import java.awt.*;

class First extends Frame {
    First() {
        Button b = new Button("click me");
        b.setBounds(30, 100, 80, 30);
        add(b);
        setSize(300, 300);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String args[]) {
        First f = new First();
    }
}
```

## Swing
Swing was introduced as a successor to AWT. It is part of the Java Foundation Classes (JFC) and is built on top of AWT. Swing components are lightweight and platform-independent, which means they have a consistent look and feel across different operating systems.

Here is a simple example of a Swing application:
```java
import javax.swing.*;

public class First {
    public static void main(String args[]) {
        JFrame f = new JFrame();
        JButton b = new JButton("click");
        b.setBounds(130, 100, 100, 40);
        f.add(b);
        f.setSize(400, 500);
        f.setLayout(null);
        f.setVisible(true);
    }
}
```

### Layout Managers
A layout manager is used to organize the components in a container. Swing provides several layout managers, including:
*   `BorderLayout`: Organizes components at the borders (North, South, East, West) and the center of a container.
*   `GridBagLayout`: Organizes components in a grid. The elements can be of different sizes and may occupy more than one row or column.

### Thread Safety in Swing
It's important to know that most Swing methods are not thread-safe. The only three thread-safe methods are `repaint`, `revalidate`, and `invalidate`. All other updates to Swing components should be done on the Event Dispatch Thread (EDT).

## JavaFX
JavaFX is a modern GUI toolkit for Java that was designed to replace Swing. It provides a rich set of features for building modern, responsive user interfaces, including support for 3D graphics, animation, and CSS styling.

While a detailed discussion of JavaFX is beyond the scope of this chapter, it's important to know that it is the recommended toolkit for new GUI development in Java.
