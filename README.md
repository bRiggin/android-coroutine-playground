# android-coroutine-playground
A collection of notes regarding Kotlin Coroutines and a simple sample project to enabled experimenting with coroutines

## Definitions
A collection of definitions or conceptual descriptions to hopfully provide clarity and context for the objects and funcations available in Kotlin Coroutines

#### Coroutines

*High Level:* Not a strict definition but can be thought of as a lightweight thread.

*More Detail:*

* Conceptually a coroutine can be thought of as a thread. _“I can run this piece of code on another thread”_ can simply be changed to _“I can run this piece of code on another coroutine”_.
    * This being said, coroutines are very computationally inexpensive compared with threads and therefore creating new coroutines is something we can do freely without having to consider the repercussions. Plus this allows us to use the “suspendable” capability of a coroutine.
* Coroutines use a concept called Continuation-Passing Style programming or CPS. Put simply, CPS involves passing the control flow of the code as an input argument to its functions and it is this mechanism that allows a coroutine to “suspend”.
    * For Kotlin coroutines are known as continuations but functionally can just be thought of as callbacks. 
* When we start a new coroutine, that coroutine is usually responsible for performing a specific task or loading some data. When, for example, that coroutine is waiting for a network request to return it becomes “suspended” and releases the underlying thread. When that result is returned, the computation is resumed and the coroutine is no longer “suspended”.
* Note that “suspendable computation” == “coroutine”
* Coroutines run “on top” of threads and as we know can be suspended. When a coroutine is suspended, the task or computation associated with it is paused, removed from that thread and stored in memory. The underlying thread is now free for other computations. 
* When it’s ready, the computation can continue and is returned to a thread, but not necessarily the same one.

#### CoroutineScope
* An object the defines the breadth or capacitity that the coroutines defind under this "scope" can operate within.
* Defines the lifecycle of a coroutine.
* This could be bound to an Applicaiton, Activity, etc and is required to create a coroutine.
* When creating a scope, you need to provide a set of Conroutine Context elements.
    * GlobeScope - Coroutines defined within this scope are only limited by the lifecycle of the application
    * CoroutineScope - Coroutines defined within this scope are limited by themselves. That is, this scope will be destroyed when all child coroutines have completed
    * MainScope - Scope for UI applications (like Android ;) ) and uses Dispatchers.Main ??? when is this destroyed, by application??

#### CoroutineContext
> "Context", "the situation within which something exists or happens, and that can help explain it"

A CoroutineContext is an object that can be created using various other objects (Job, CoroutineDispatcher, etc) and aims to help desribe/define the situation in which Coroutines will run.

#### Job
* A Job represents some work that has to be done
* Any Job can be cancelled and therefore has a lifecycle and can also have child Jobs within it
* If a parent Job is cancelled, all child jobs will be cancelled
* Likewise if a child Job fails, then the entire hierarchy also fails
* Once cancelled, you cannot reuse or restart a Job

#### CoroutineDispatcher
Defines the thread or threads that a Coroutine will use.
* Unconfined - A coroutine dispatcher that is not confined to any specific thread. It executes the initial continuation of a coroutine in the current call-frame and lets the coroutine resume in whatever thread that is used by the corresponding suspending function, without mandating any specific threading policy. Nested coroutines launched in this dispatcher form an event-loop to avoid stack overflows.
* Default - The default CoroutineDispatcher that is used by all standard builders like launch, async, etc if no other dispatcher is specified.
* IO - The CoroutineDispatcher that is designed for offloading blocking IO tasks to a shared pool of threads
* Main - A coroutine dispatcher that is confined to the Main thread operating with UI objects. Usually such dispatchers are single-threaded.

#### Corountine Builders
* Builders take a suspending lambda as an input and create a coroutine for that lambda to run in
* There are some common builders included in the coroutine API (
    * async - Creates a coroutine and returns its future result a deferred value. The running coroutine is cancelled when the resulting deferred is cancelled. The resulting coroutine has a key difference compared with similar primitives in other languages and frameworks: it cancels the parent job (or outer scope) on failure to enforce structured concurrency paradigm. To change that behaviour, supervising parent (SupervisorJob or supervisorScope) can be used.
    * launch - Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a Job. The coroutine is cancelled when the resulting job is cancelled.
    * runBlocking - Runs a new coroutine and blocks the current thread interruptibly until its completion. This function should not be used from a coroutine. It is designed to bridge regular blocking code to libraries that are written in suspending style, to be used in main functions and in tests.

#### Suspending Functions
* A function that can be suspended without blocking the thread it was called form
* As well as a return value, it also knows the context in which it was called from, therefore allowing it to return appropriately.

#### Exceptions
##### CoroutineExceptionHandler
Description to be provided 

##### Builder Dependant Behviour
This behave differently depending on the Coroutine Builder that is being used:
* lauch
    * This will propagate the exception to the parent and will fail the hierarchy
    * This will throw an exception within the coroutine thread immediately
    * Should be handled with try/catch or custom exception handler
* async
    * This will defer the exception to the calling async block
    * If the result of the aysnc block does not consume the result through await then exception can be missed
    * Should always call await() from a try/catch block or within a scope that has a custom CoroutineExceptionHandler

#### Coroutine
Not a strict definitions but can be thought as a lightweight thread

## Knowledge Nuggets

### funnny title that's like "Bridging blocking and non-blocking worlds"

Unfortunely vanillia Android doesn't live in the non-blocking world but rather on the main thread. In order to utilise the wonderful goodness of non blocking Coroutines, we have to throw our code into their world.
