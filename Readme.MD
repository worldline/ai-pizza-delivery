# AI assisted Coding - Kickoff
<<<<<<<< Status Alpha, first version for review, learning ... >>>>>>>>

Welcome on board!

The purpose of this project is, to provide some background on AI assisted coding 
and to give you a fast start for usage. The initial version of the guide is based on experience with Codeium and GitHub Copilot integrated with JetBrains IntelliJ and
results and suggestions might not be fully transferable to other AI assistants / IDEs. Generally I assume you have access to a
`inline completion` feature and a `chat function` with history and code awareness (reference files, classes, functions).

By the way this project has been mostly created with an AI, not a recommended workflow but an interesting
experience. In a next version of the guide there might be a walkthrough.

Important: all shared opinions are my own and I do not speak for Worldline<br>
Contribution: see [Readme-Contribution](./README-CONTRIBUTION.md)

## How does Generative AI work?

If you want to get real insights: invest some time, look some Worldline Labs content, browse the Internet.
But in layman's terms text based AI learnt to create sound and natural text snippets for a given context.

* if you ask it a question it will reply with an answer that could be true, one that you could believe
* if you give it a partially blackened out text it will fill it with something that could have been there
* and interestingly it seems to be able to apply logic, it seems to reason and seems to understand 

This encapsulated knowledge is called the model. The model can be made more specific by fine-tuning it with
additional training, for instance from general text towards programming languages or from programming languages
towards your companies code styles, patterns and helpers.

Training a model requires massive amounts of compute and data, fine-tuning is still quite expensive and even applying 
a model will still require a beefy piece of hardware - except if you compromise and reduce the quality of your model,
then it can even run on your laptop. Albeit the quality suffers and finding the right models, tuning them and making
them efficient is an area of active and fast moving research.

Text models work with a context i.e. there is a certain "memory" buffer that influences the results of your query. 
This can be the conversation that you have, your open source file, a source file that you reference or even your local
code base. This context heavily influences your results, for instance in a conversation you might tell the model why
you are not satisfied with a result and how it could be improved. In contrast to training and tuning the context
is more limited in size, but currently (end of 2024) most practical limitations have been lifted and many thousand
words can be processed.

## What should be my takeaway from this?

* the quality of the results that you see is evolving fast, what was true yesterday might not be true tomorrow
* an AI itself does not understand, it processes text BUT results can be damn close to understanding
  (and philosophically where is the boundary?)
* an AI will answer, even if the answer is just a hallucination e.g. it can dream up the API call that would fit
into a given library
* but a Code AI likely is more than just a text model, we don't know which secrete sauce the providers adds to the mix,
but it is reasonable to expect some knowledge or plausibility checks, it could even verify against the real source code of a library

#### on top of this
* context is very important, results based on your current source file will be much more accurate, pasting in context can also help 
* this is all cutting edge, the IDE, the version of the IDE, the plugin, the selected model quality level - all of it can make a difference
* do not try to get a 100% accurate result, follow the 80/20 rule and add the last 20% manually
* generative AI should be used in an iterative workflow, you start at one point, then refine manually or give feedback to the AI

The last point cannot be stressed enough, for instance this famous, award-winning [AI image](https://arstechnica.com/information-technology/2023/09/us-rejects-ai-copyright-for-famous-state-fair-winning-midjourney-art/)
took its creator over 600 iterations and manual interventions! <b>thus:</b>


* using type-along completion don't expect a 100% correct suggestion
90% + fast manual adjustments are just fine
* if in chat mode provide the right context == your code, the class you are working on, the framework you want to use 
* if in chat mode make use of follow-up queries until a sufficient level is reached (1-3 queries) then switch to manual polishing 

## Hands On - Use Cases

1) <b>to generate natural text</b> in a pre-specified format: test data, example data,
   dummy data for mocks ....  for instance try this:
   ```
      I need some test data, can you generate me a list of 10 well known pizzas
      and two funny pizzas using this template:

      "Hawai:Tomato,Pineapple,Cheese,Ham"
   ```
   This is how I created the `resources/*.data` files. Play around for the ingredients! End of 2023 the Code AIs had some issues with this task but ChatGPT worked great.
   Now (end of 2024) it works well in IntelliJ too. This is also an excellent example for iterations, if the format is not respected, try to correct the AI.


2) <b>to explain things to you</b> either directly "button next to your code" or by referencing the relevant snippet in
   the chat or in the worst case by copying it over (here ChatGPT would NOT be ok)<br><br>
   
   Take a look at the `DataParser`, IntelliJ and most other IDEs will call you out for using `printStackTrace()` 
   ```java
   try (BufferedReader reader = new BufferedReader(new FileReader...) {
       String line;
       while ((line = reader.readLine()) != null) {
           data.add(line);
       }
   } catch (IOException e) {
       e.printStackTrace();
   }
   ```
   Ask your AI about this code snippet, what's wrong with it? Chances are you get some hints in the direction of
   logging frameworks. Now feed the AI some context, tell it what you use and ask for advice like this:
   ```
   >> AI: best practice would include to use a more robust form of logging
   
   << I use Log4J, can you show me how to replace the printStackTrace in the code?
   ```
   (with GitHub Copilot you can mark the lines in question and ask for help with the context-sensitive chat function)


3) <b>type along</b> improving or worsening the inline suggestions while you code. This is clearly the most accessible
   feature and if you make use of shortcuts to paste in (partial) suggestions and switch between alternatives you can save time. But
   AI inline completion currently rather fights with IDE build in completion instead of augmenting it. This is probably why they toned it down
   quite a bit recently in Copilot. 

   For an example navigate to DataServiceTest and drop these lines from testGetPizza

   ```java
   List<Pizza> expectedPizzas = List.of(
       new Pizza("Hawai", List.of(
                   new PizzaIngredient("Tomato"),
                   new PizzaIngredient("Pineapple"),
                   new PizzaIngredient("Mozzarella")
                )),
       new Pizza("Pepperoni", List.of(
                   new PizzaIngredient("Tomato"),
                   new PizzaIngredient("Mozzarella"),
                   new PizzaIngredient("Pepperoni")
                ))
        );
   ```
   Now start adding it again, type don't copy! What you should see is that the code editor understands you
   better and better while you fill out the code. With Codeium the impressive part was that it did pick up the
   context from `List<String> pizzas` a few lines earlier and correctly suggested the Pepperoni pizza including ingredients.
   Try it out, replace Pizza Pepperoni with Pizza Kiwi, repeat and check how/if the suggestions change.

   If the suggestions don't start flying at you try to trigger them using the context-sensitive chat button or ask for 
   suggestions with the right shortcut.

4) <b>comment coding</b> we learnt about test driven development, diagrams first, API first 
   and now with AI coding assistants it turns out comments before code is a useful pattern too. Remember, a key learning 
   for effective usage is to feed the AI context. Try to express in a comment what you want to code and 
   improve your inline suggestions with this little hack!<br><br>

   For instance in the Data Parser class you find the following code snippet
   ```java
   try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get("src/main/resources/" + fileName).toFile()))) {
        String line;
        while ((line = reader.readLine()) != null) {
            data.add(line);
        }
   } catch (IOException e) {
        e.printStackTrace();
   }
   ```
   delete it and try to re-create it through comment coding (experiment a bit, does the result differ if you drop "Java"?).
   ```
   // read the content of a text file from the Java resources folder and store it in the data list
   try (Buff ...
   ```
   This technique is especially good if you know roughly what you want to implement and which tools you want to
   use, but you lack a piece of syntax, say in our example you forgot how to best reference the `resource` folder again.
   But be warned, the 80/20 rule applies and verification of the created snippet is a MUST. The correctness 
   of the code, edge case coverage ... are suboptimal. For example good luck to get your AI to correctly guess the
   equals method in the `Pizza` class, an algorithmic and not a syntactical challenge. Also remember that the inline
   suggestions are not a chat interface, write actual comments, not questions or queries!


5) <b>code snippet generation</b> for larger pieces of functionality with iterative improvements - using the chat functionality.
   The idea is that you use the AI chat to create a whole code skeleton and refine the results by pasting in syntax errors,
   warnings and additional hints until you are satisfied.
   This is how most of the PizzaDelivery project was created but there are some things to consider: <br><br>
 
    - either you have no clue and would anyway just copy over code from stackoverflow until it works and it doesn't have
      to be the best / most robust solution. In this case the bar is low and the AI passes it easily. Let's assume you
      switched from Maven to Gradle
      ```
      can you create me a build.gradle file, I need to build it with Spring Boot and the project will contain a REST
      interface and some JSON wrapped data. From an infrastructure perspective I want to use mavenCentral
      and Junit for testing. 
      ```
      You could also start with a Maven script as context or copy over a quickstart example
      or something from StackOverflow. The neat thing is based on the context you now can start
      asking questions like `what is source compatibility?` or try to figure out how to set up your private
      repository instance ... since this is a conversation you will also have to correct the AI if it takes detours.
      <br><br>
    - another use case is where you have a lot of knowledge, you don't want to just shorten the google-copy-paste loop
      but instead want to create larger pieces of code. This requires a lot more elaborate queries to provide the right
      context and you must be expert enough to spot and correct the 20% on the fly. As said most of this demo projct
      has been created like that but I doubt that I saved any time or improved the quality )-:
      <br><br>
    - finally a word of warning, where it really doesn't work well is if you try to do skeleton code generation but lack
      expertise in the language and frameworks. In this case you might easily end up in incorrect code or code that builds
      on anti-patterns.
 

6) <b>comment generation</b> is finally a nice addon that some of the tools provide. Especially for boilerplate Java Doc
   you can save some seconds for the generation. Again this tends to work better with context, for instance in the Pizza
   class there is a special equals method. Try to crate the Java Doc:
   ```java
   /**
     * Determines whether this Pizza object is equal to another object.
     *
     * @param  o  the object to compare to
     * @return    true if the objects are equal, false otherwise
   */
   ```
   Then tell the AI to pick up the details embedded in inline comments. For instance this works in Codeium (some other similar queries don't work^^)
   ```
      please be more specific about when a pizza is equal to another pizza
   ```
   and the result would be something like this
   ```java
   /**
     * Determines whether this Pizza object is equal to another object.
     *
     * @param  o  the object to compare to
     * @return    true if the objects are equal, false otherwise
     * 
     * This method compares this Pizza object to another object to determine if they are equal in terms of their name and ingredients.
     * It accepts two pizzas as equal if either the name or the ingredients are equal.
     * However, it accepts the ingredients as equal if they have the same elements, regardless of their order.
     * In this case, the method ignores duplicates in the lists and assumes that unique elements are present.
     *
     * @see #equals(Object)
     */
   ```   

## Further Reading (by Worldliners)

limited to content by our peers at Worldline, there is to much Internet content for a curated "good to read" list (-; 

* [How Open is Generative AI](https://blog.worldline.tech/2023/10/25/open-gen-ai.html)
* [Private Chat Assistant - gpt4all](https://dev.to/worldlinetech/introducing-gpt4all-28a1)