# Chapter 3: Objects - Creating Your Own Things

---

## The Left Page: A Picture of a Blueprint and a Robot

**(Imagine a picture here of a blueprint for a robot on the left, and a real, physical robot on the right.)**

### The Code Spell

Java the Wizard says: "So far, we've only used simple variables like numbers. But what if we want to create our own things, like a robot? For that, we need a **class** and an **object**."

First, we create the blueprint (the **class**):
```java
class Robot {
    String name;
    String color;

    void sayHello() {
        System.out.println("Hello, my name is " + name);
    }
}
```

Then, we build a robot from the blueprint (create an **object**):
```java
Robot myRobot = new Robot();
myRobot.name = "Robby";
myRobot.color = "blue";
myRobot.sayHello();
```

---

## The Right Page: The Story of the Code

### 1. The Story

We want to build a robot. First, we need to draw a blueprint that describes what all robots will have (a name and a color) and what they can do (say hello). This blueprint is called a **class**.

Then, we can use the blueprint to build as many robots as we want. Each robot we build is an **object**.

### 2. The Code Explained

*   `class Robot { ... }`: This defines the `Robot` blueprint.
*   `String name; String color;`: These are the variables inside our class. They are the "properties" of our robot.
*   `void sayHello() { ... }`: This is a "method". It's an action that our robot can do.
*   `new Robot()`: This is the magic spell to create a new robot object from our class.
*   `myRobot.name = "Robby";`: We use the dot (`.`) to access the properties and methods of our object.

### 3. Under the Hood: Inside the JVM House

Java the Wizard says: "This is where the **Downstairs Toy Box** (the **Heap**) comes in!"

1.  When you create the `myRobot` variable, a new suitcase is created in the **Upstairs Room** (the **Stack**).
2.  But this suitcase doesn't hold the robot itself. It holds a magic remote control that points to the robot.
3.  The robot **object** is created in the **Downstairs Toy Box** (the **Heap**). This is a big toy box where all your objects live.
4.  When you use the dot (`.`), you're using the remote control to tell the robot in the toy box what to do.

**(Imagine a cartoon picture of the JVM House. A suitcase labeled "myRobot" is in the Upstairs Room, with an arrow pointing to a robot toy in the Downstairs Toy Box.)**

---

### Your Next Experiment!

**Predict:** Can you create another robot object with a different name and color?

**Run:** Try it out in an online Java sandbox!

**Reflect:** Draw a picture of the JVM house with two robot toys in the Downstairs Toy Box.
