# Stock portfolio pie charts

## Summary

Demonstration gif:
<br /> ![image01](/imgs/demo.gif)

Project incentives:
- What will the application do? It takes inputs of stock investment data from a user, and plots pie charts for data visualization and organizing.
- Who will use it? People with a few stocks in their portfolio and want to better visualize them for future decisions.
- Why is this project of interest to you? I have some investment in low-risk funds, and occasionally have need to visualize my portfolio. A lot of stock apps can do similar things and much more, but it is nice to know whether I can get a start at doing such things too.

User stories:
- As a user, I want to be able to **input** an arbitrary number of my invested stocks (X) into a list (Y).
- As a user, I want to input data about those items, including: **name**, **invested money**, **price**.
- As a user, I want to be able to **delete** those items.
- As a user, I want to be able to **update** those items, such as: change prices or invested money on it.
- As a user, I need to see a **list** of stocks with their info in each row, or something like that.
- As a user, I want to save inputted stock info items in a file on hard drive.
- As a user, I want to load the file I saved last time.
- *(Stretch goal)* As a user, I may want to see pie charts for 2-3 months juxtaposed together for comparison.

## Construction
Potential points for refactoring:
- Split StockDataHandler to a data handling class and a data updating class (√)
- The data handling class could be made adhering to the Singleton pattern (√)
- The data updating class could be made adhering to the Observer pattern (?)
- ~~Maybe can use Composite pattern to make adding JTextField and JButton objects in the add new item window more organized~~
- Aggregate those illegal input value checks into an exception control flow

Class diagram (before any refactoring):
<br /> ![image01](/UML_Design_Diagram.jpg)