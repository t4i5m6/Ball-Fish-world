# Ball and Fish world
hw4-t4i5m6 created by GitHub Classroom

Slip days used: 1

Heroku: https://tc78-hw4-collision.herokuapp.com/



Model:

    resilient/fault tolerant:
        Model will not add any ball and not crash when view sends a unknown type ball or unknow type strategy.
    extensible:
        Used stratey design pattern to handle different behaviors and interactions, If need new behavior or interaction, just add a new class and add itself to StrategyFactory, which is easy to scale.
        
        
    UML Diagram is located at /src.
View:

    User-friendly:
    
        Collect all actions to let users select, and when it selects, action button name will become action name.
        This decreases the number of buttons same as strategies.
        
        Only show needed select parts when a user selecets different actions.
        This make users not confused about not-used options.
        
        Add labels to all selectable options.
        
        Show prioirty of interactions on the text of options. 
        Interactions have high priority will have effect when encountering the ball has a lower priority interaction.
        When prioirty is same, balls will interact using change direction interaction.
        This make users easy to play interactions.
     
     extensible:
     
         When adding a new behavior or interaction, just add a option at the index.html. Don't need to change anything in the view.js, which is scalable.
        
    
Controller:

    robust and extensible solution for endpoints:
    
        When adding a new paint object, controller just sends the type and strategy to adapter,
        so it wont need to add any endpoints for a new type or a new strategy, which is extensible and robust.
     
    resilient/fault tolerant to bad requests:
        When a bad request occurs, it will be redirected to the home page and not crash.
     
