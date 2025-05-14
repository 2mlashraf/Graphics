import turtle
import random

# Setup the screen
screen = turtle.Screen()
screen.bgcolor("black")
screen.title("Night Sky with Crescent Moon and Shooting Star")

# Draw stars
stars = turtle.Turtle()
stars.hideturtle()
stars.speed(0)
stars.color("white")

for _ in range(100):
    x = random.randint(-400, 400)
    y = random.randint(-300, 300)
    stars.penup()
    stars.goto(x, y)
    stars.dot(random.randint(1, 3))

# Draw crescent moon (two overlapping circles)
moon = turtle.Turtle()
moon.hideturtle()
moon.penup()
moon.goto(100, 50)  # Moon position
moon.color("lightgray")
moon.begin_fill()
moon.circle(60)
moon.end_fill()

# Cover part of the moon to form the crescent
mask = turtle.Turtle()
mask.hideturtle()
mask.penup()
mask.goto(120, 60)  # Slight offset to the right
mask.color("black")
mask.begin_fill()
mask.circle(60)
mask.end_fill()

# Draw a shooting star
shooting_star = turtle.Turtle()
shooting_star.hideturtle()
shooting_star.speed(0)
shooting_star.color("yellow")
shooting_star.pensize(2)

# Shooting star starting position
shooting_star.penup()
shooting_star.goto(-200, 200)
shooting_star.pendown()
shooting_star.setheading(-25)  # Angle of movement
shooting_star.forward(100)     # Length of the shooting line

turtle.done()
