# 基于TestNG的测试框架练习与分析

**目录 (Table of Contents)**

   * [基于TestNG的测试框架练习与分析](#基于testng的测试框架练习与分析)
      * [1. 单功能案例](#1-单功能案例)
      * [2. 断言设置](#2-断言设置)
      * [3. 数据驱动](#3-数据驱动)
      * [4. 流程案例](#4-流程案例)
      * [5. 数据传递](#5-数据传递)
      * [6. 任务与用例集](#6-任务与用例集)
      * [7. 案例运行前置与后置处理](#7-案例运行前置与后置处理)
      * [8. 多类型自动化运行](#8-多类型自动化运行)
      * [9. 执行调度](#9-执行调度)
      * [10. 汇总总结](#10-汇总总结)

## 1. 单功能案例
对应工程中的文件：

` SingleTest.java`

先从一个简单的接口调用开始。新建TestNG工程，TestNG提供@Test标签，在运行测试时，会执行@Test标签中的代码，代码当中编写相关的测试逻辑。示例中通过引入相关库，编写基础的调用过程，完成了一个简单的Http的Get请求的发起。
![](https://github.com/DingTobest/AutomatedTestExercises/blob/master/pic/1.1.png?raw=true)
通过示例可以看到，TestNG基础的功能仅在测试执行的调用上，基础测试代码如果不进行封装，是比较复杂的。作为对照功能，我们通过Postman，发起单接口的测试调用，填入URL与相关参数，非常快速的就可以完成一次简单接口的测试过程。所以TestNG需要针对测试业务开发自动化测试框架，使测试过程的功能调用更加简便，直接进行代码编写，提供的灵活度也是最高的。测试工具在简单测试场景，如简单接口，UI自动化的录制回放上面，优势比较明显。
![](https://github.com/DingTobest/AutomatedTestExercises/blob/master/pic/1.2.png?raw=true)
## 2. 断言设置
对应工程中的文件：

`AssertTest.java`
TestNG中断言分类两类：第一类为硬断言；第二类为软断言。硬断言在执行时，判断执行失败后，会停止后续的代码的运行。软断言在执行时，判断失败后并不会停止后续的代码运行，在案例全部执行结束后，一次性将所有的断言失败结果抛出。
|断言 | 描述 |
| ------------- | ------------- |
| Assert.assertEquals | 判断元素是否相同 |
| Assert.assertEqualsNoOrder | 判断元素在忽略顺序的情况下是否相同 |
| Assert.assertFalse | 判断元素是否为False | 
| Assert.assertNotNull | 判断元素是否不为Null |
| Assert.assertNotSame | 判断元素是否地址不同 |
| Assert.assertNull | 判断元素是否为Null |
| Assert.assertSame | 判断元素是否地址相同 |
| Assert.assertTrue | 判断元素是否为True |
| softAssert.assertEquals | 软断言，判断元素是否相同，如果不通过，也不终止程序，继续执行。 |
## 3. 数据驱动
对应工程中的文件：

`DataProviderTest.java`

TestNG的@DataProvider标签，可以实现数据驱动功能。

@DataProvider标签通过返回一个Object[][]，可以提供多组测试数据。在@Test标签中，通过指定dataProvider，对应到@DataProvider的名称，并且在@Test标签下的函数中，提供参数，对应到@DataProvider函数的返回值的内容，使得在@Test标签运行时，能够完成数据驱动功能。

从截图可以看到虽然只有一个@Test标签，因为提供的数据包含两组，@Test对应的测试函数也被执行了两次。通过数据驱动的方式，可以大大提高脚本的复用性。

在自动化测试平台中，数据驱动也是非常重要的设计实现，并且可以搭配数据管理，完成对自动化测试过程中的数据提供。
![](https://github.com/DingTobest/AutomatedTestExercises/blob/master/pic/3.1.png?raw=true)

## 4. 流程案例
对应工程中的文件：

`FlowCaseTest.java`

在TestNG中，提供@Test标签中的参数priority，可以对相关的案例执行顺序进行排序。我们通过示例可以看到，执行过程按照priority标签的定义进行执行，通过这样的方法，可以完成流程用例的调用。

自动化测试工具在这个过程中的可视化能力，可以提供非常好的帮助，通过流程图等形式，可以很好的进行流程配置与流程管理，搭配针对流程所提供的测试数据，可以非常好的满足流程案例的配置和管理需求。
![](https://github.com/DingTobest/AutomatedTestExercises/blob/master/pic/4.1.png?raw=true)

## 5. 数据传递
对应工程中的文件：

`DataTransferPool.java，DataTransferTest.java`

在流程案例的执行过程中，必定会遇到数据传递的问题。比如下一个案例需要使用上一个案例所对应的流水号等场景。TestNG框架自身不包含数据传递功能，需要通过变量等形式，对数据进行传递。以下是对数据传递的一种封装，核心数据结构是Map，通过Key来进行数据的存储和使用。
![](https://github.com/DingTobest/AutomatedTestExercises/blob/master/pic/5.1.png?raw=true)
![](https://github.com/DingTobest/AutomatedTestExercises/blob/master/pic/5.2.png?raw=true)

自动化测试平台中，也必须要完成相关的数据传递的功能。在功能设计时，经常会存在两种设计方法，一种是点对点的数据传递，另外一种是数据池的设计方式。点对点的设计就是某一流程节点中的相关参数，指定传递到后续的某一流程节点的变量中。数据池的设计方式与上面的代码封装类似，核心也是以map结构为设计方式，数据产生时存储在数据池中，使用数据时通过数据池获取数据。

我在设计的过程中，基本都是以数据池的形式进行设计。因为数据池将数据生产方与消费方进行了隔离，数据传递更加直接，更容易维护。
## 6. 任务与用例集
对应工程中的文件：

`TestSuiteTest1.java，TestSuiteTest2.java，TestSuiteTest.xml`

`TestGroupTest.java，TestGroupTest.xml`

任务更倾向于是一个触发性事件，用例集更倾向于集合。在持续集成过程中，以用例集的形式，可以更好的与流水线进行对接，因为测试执行是被调用方，调用时间、次数均不固定，以用例集的形式可以更好的满足这个条件。任务多有一次性的概念在当中，不能特别好的满足被调用的场景。在线性的执行过程中，两者差别并不明显。

TestNG中，TestSuite是用例集的概念。上面我们在测试的过程中，都是使用同一个class文件进行的测试。实际测试过程中，为了便于管理，会有多个class文件存储案例的内容。TestNG通过配置文件的形式，可以将多个class文件放在一个suite中。这样，TestNG在运行时，会根据配置文件的内容，调用到所有相关的测试类。

![](https://github.com/DingTobest/AutomatedTestExercises/blob/master/pic/6.1.png?raw=true)
![](https://github.com/DingTobest/AutomatedTestExercises/blob/master/pic/6.2.png?raw=true)
![](https://github.com/DingTobest/AutomatedTestExercises/blob/master/pic/6.3.png?raw=true)

在测试过程中，一组测试用例也会有不同的用途，最常见的就是一组用例中，挑选出来较少的用例，作为冒烟测试，冒烟测试通过后，再运行全量的用例。TestNG中提供TestGroup，在@Test标签中，可以指定案例所属的分组，一个案例可以属于多个分组。在配置文件中，可以指定当前需要运行的用例组，这样，就可以将用例进行划分，更好的满足不同的测试场景。

![](https://github.com/DingTobest/AutomatedTestExercises/blob/master/pic/6.4.png?raw=true)
![](https://github.com/DingTobest/AutomatedTestExercises/blob/master/pic/6.5.png?raw=true)

TestNG在TestSuite与TestGroup的设计，我认为自动化测试平台完全可以借鉴。自动化测试平台通过用例集，发起自动化测试，并且可以将用例集开放出来，供持续集成流水线进行调用。用例集的执行结果，作为历史运行结果，保存在用例集当中。

## 7. 案例运行前置与后置处理
对应工程中的文件：

`AnnotationsTest1.java，AnnotationsTest2.java，AnnotationsTest.xml`

自动化在运行前，无论是环境准备还是数据准备，都有不少环节要做。TestNG提供了一组标签，用于控制在自动化执行过程中的顺序。这些过程通过一张图的形式，还是比较容易看出来。途中代表着两个class文件当中的测试用例，在TestNG的执行过程中，与相关标签的顺序关系。

| Annotationsg关键字 | 描述 |
| ------------- | ------------- |
| @BeforeSuite | 被注释的方法将在所有测试运行前运行 |
| @AfterSuite | 被注释的方法将在所有测试运行后运行 |
| @BeforeTest | 被注释的方法将在测试运行前运行 |
| @AfterTest | 被注释的方法将在测试运行后运行 |
| @BeforeGroups | 被注释的方法将在列表中的Group前运行。这个方法保证在第一个属于这些组的测试方法调用前立即执行 |
| @AfterGroups | 被注释的方法将在列表中的Group后运行。这个方法保证在第一个属于这些组的测试方法调用后立即执行 |
| @BeforeClass | 被注释的方法将在当前类的第一个测试方法调用前运行 |
| @AfterClass | 被注释的方法将在当前类的第一个测试方法调用后运行 |
| @BeforeMethod | 被注释的方法将在每一个测试方法调用前运行 |
| @AfterMethod | 被注释的方法将在每一个测试方法调用后运行 |
| @DataProvider | 在需要提供数据的测试方法调用前进行一次调用 |

![](https://github.com/DingTobest/AutomatedTestExercises/blob/master/pic/7.1.png?raw=true)
自动化测试平台在设计时，也需要通过对用例执行的前置与后置处理，来满足不同案例运行所需要的正确信息。
## 8. 多类型自动化运行
代码级的天然优势，代码控制，灵活度最高。

主流的Selenium与Appnium都可以通过java或python进行调用，针对接口也会有对应的包可以进行调用，如果代码级对不同的测试类型进行调用，还是比较简单的。

如果不同的测试工具，自动化实现语言有差异的时候，代码级也会遇到一定的困难。

自动化测试平台在针对这样的情况时，接口、Web、移动端等由于会出现不同的工具，这些工具由于脚本设计的差异性，会导致各个平台进行集成比较困难。

所以，类似于selenium、Appnium与接口等，通过java或python这样的语言平台，实现了统一化运行，自动化测试平台也需要通过实现抽象语言层或抽象工具层，将各个平台的差异进行统一，这样，多类型的自动化测试才能比较好的进行执行。

## 9. 执行调度
对应工程中的文件：

`ParallelTest.java，ParallelTest.xml`

当自动化执行的测试案例量比较大的时候，自动化测试执行的调度是一个非常重要的部分，直接影响着自动化执行的效率。

TestNG没有提供执行调度功能，仅提供了多线程的功能。

| 影响内容 | 描述 |
| ------------- | ------------- |
| suite | 通过控制台进行多suite调用。 |
| tests | 针对testng配置文件中不同test标签分配线程，同一个test标签下同一个线程。 |
| classs | 针对testng配置文件中不同class标签分配线程，同一个class标签下同一个线程。 |
| methods | 所有案例运行时都使用独立的线程去运行。 |

![](https://github.com/DingTobest/AutomatedTestExercises/blob/master/pic/9.1.png?raw=true)
![](https://github.com/DingTobest/AutomatedTestExercises/blob/master/pic/9.2.png?raw=true)

测试过程中使用methods标签,分配线程数量为5，data-provider线程数为5，增加多个用例，并输出当前线程号与用例编号，我们可以看到启动了多线程进行执行。

TestNG在自身的执行调度方面比较弱，可以通过搭配Jekins、Git、Maven，进行测试执行调度。

在这一部分，自动化测试平台在管理与调度能力上优势体现的比较明显，可以通过测试执行机的管理与本身的自动化案例脚本管理相结合，进行大规模的并发自动化测试，具备明显的优势。

## 10. 汇总总结
通过对TestNG相关功能的展示，同样，自动化测试平台应该具备其相关功能才可以满足自动化实施过程中的不同场景。TestNG的整体设计，在自动化测试平台设计过程中，也有非常大的参考价值，如何吸收TestNG这样测试框架的优秀设计，再将自动化测试平台的优势再发挥出来，是需要不断思考的，以下是我的一些总结。

作为像TestNG这样的测试框架，直接通过代码编写的方式进行执行，无疑提供的灵活度是最高的，很多复杂实现，都可以通过编程的方式进行实现，是非常直接的一种方式。通过对自动化测试的相关功能进行封装，非常好的适应不同的测试场景。作为自动化脚本代码的编辑，IDE提供的代码编写与调试能力，是非常灵活的，在这部分，是很难超越的。

但这种方式带来的一定的弊端，首先就是可视化能力比较差，其他资产的相关联也比较差一些，测试框架的功能相对单一，需要搭配其他的工具、系统，共同完成类似于管理和执行调度类功能。其次，在测试场景非常简单的情况下，比如仅仅调用一个接口时，像Postman这样的工具会非常简单，会比代码编写要简单的多。

针对以上的描述，总结以下内容：

- 测试框架可以满足不同测试场景的需求，平台需要支持框架相关功能
- 脚本编写自动化代码，灵活度最高，复杂场景可以较好实现
- IDE的开发与调试能力，平台很难达到
- 工具、平台的可视化非常友好
- 工具、平台在简单功能快速测试上具备优势，在脚本辅助生成方面空间较大
- 平台在资产与管理方面能够提供较好的功能
- 平台在执行调度的灵活性较高

根据以上的几种情况，接口测试是自动化测试当中标准化程度最高的，脚本的复杂度远远低于UI自动化脚本，所以在脚本编辑方面的弱势可以最大程度的规避掉，是最容易进行平台化的部分。
