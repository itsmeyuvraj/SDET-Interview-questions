# Java Automation QA / SDET Interview Preparation Maven Project

A curated, comprehensive Java Maven project containing all the most frequently asked programming problems and architectural patterns in **Automation QA, SDET (Software Development Engineer in Test), and Test Automation** technical interviews.

Every file is fully self-contained, includes detailed explanations of the interviewer's intent, optimal vs. brute-force approaches, time/space complexity analysis, and a `public static void main(String[] args)` method for 1-click execution.

---

## 🚀 How to Import into IntelliJ IDEA

This project uses the standard Maven structure and is configured to import seamlessly into IntelliJ IDEA without requiring manual classpath setups:

1. Open **IntelliJ IDEA**.
2. Click **File > Open...** (or click **Open** on the Welcome screen).
3. Navigate to and select the folder:  
   `/Users/yuvraj/Documents/Java`
4. Click **Open**.
5. When prompted:
   - Select **"Open as Project"** or **"Trust Project"**.
   - IntelliJ will automatically detect `pom.xml`, download JUnit 5 dependencies, and configure the source directories (`src/main/java`, `src/main/resources`, and `src/test/java`).
6. **To Run Any Program:**  
   Open any `.java` file and click the green ▶ play icon next to `public static void main` or the class definition.
7. **To Run All Unit Tests:**  
   Right-click `src/test/java/com/automation/qa/InterviewProgramsTest.java` and select **"Run 'InterviewProgramsTest'"**.

---

## 📚 Master Index of Interview Programs

### 1. String Manipulation (`com.automation.qa.strings`)
The most heavily tested category in Automation QA technical rounds.

| File | Problem Statement & Concepts | Primary Interview Focus |
| :--- | :--- | :--- |
| [`ReverseStringVariants.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/strings/ReverseStringVariants.java) | Reverse string using StringBuilder, Two-Pointers (`char[]`), and **preserving space positions**. | String immutability, in-place pointer swapping, handling spaces. |
| [`ReverseWordsInSentence.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/strings/ReverseWordsInSentence.java) | Reverse word order ("Hello World" -> "World Hello") and reverse each word in place ("olleH dlroW"). | Regex `\\s+` splitting, boundary conditions, whitespace handling. |
| [`PalindromeCheck.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/strings/PalindromeCheck.java) | Simple palindrome & alphanumeric palindrome ignoring casing and punctuation (LeetCode 125). | Two-pointer technique, `Character.isLetterOrDigit()`, O(1) space. |
| [`AnagramCheck.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/strings/AnagramCheck.java) | Check if two strings are anagrams using sorting, 256-ASCII frequency array, and HashMap. | Explaining why frequency array achieves O(n) time and O(1) space. |
| [`CharacterCountAndDuplicates.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/strings/CharacterCountAndDuplicates.java) | Character occurrences using HashMap, printing duplicates, and Java 8 Streams grouping. | `Map.getOrDefault()`, stream collectors, duplicate filtering. |
| [`FirstNonRepeatedChar.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/strings/FirstNonRepeatedChar.java) | Find the first unique character in a string (e.g. "swiss" -> 'w'). | `LinkedHashMap` (insertion order) vs `HashMap`, two-pass frequency array. |
| [`RemoveDuplicatesAndSpecialChars.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/strings/RemoveDuplicatesAndSpecialChars.java) | Remove duplicates preserving order, sanitize strings via regex, extract prices from UI. | `LinkedHashSet`, regex character classes, test data cleansing. |
| [`StringCompression.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/strings/StringCompression.java) | Run-length encoding ("aaabbc" -> "a3b2c1") and decompression. | Loop boundary checking, running counts, StringBuilder. |
| [`SwapStringsWithoutTemp.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/strings/SwapStringsWithoutTemp.java) | Swap two strings without declaring a 3rd temporary variable. | `concat` and `substring(start, end)` index arithmetic. |
| [`LongestSubstringWithoutRepeating.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/strings/LongestSubstringWithoutRepeating.java) | Find length and value of longest substring without repeating characters (LeetCode 3). | Sliding window algorithm, HashMap index tracking, O(n) runtime. |

---

### 2. Arrays & Sorting (`com.automation.qa.arrays`)

| File | Problem Statement & Concepts | Primary Interview Focus |
| :--- | :--- | :--- |
| [`SecondLargestAndSmallest.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/arrays/SecondLargestAndSmallest.java) | Find second largest & smallest element in single pass **without sorting**. | O(n) single pass, handling duplicate max elements gracefully. |
| [`FindDuplicatesInArray.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/arrays/FindDuplicatesInArray.java) | Find duplicates using HashSet, sorting, and stream filtering on test record IDs. | `HashSet.add()` return value, time vs space tradeoffs. |
| [`MissingNumberInArray.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/arrays/MissingNumberInArray.java) | Find missing number from 1 to N using Sum formula and bitwise XOR. | Bitwise XOR cancellation, avoiding integer overflow with large N. |
| [`MoveZeroesToEnd.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/arrays/MoveZeroesToEnd.java) | Shift all 0s to array end while preserving non-zero relative order in-place (LeetCode 283). | Two-pointer in-place array manipulation, O(1) auxiliary memory. |
| [`TwoSumProblem.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/arrays/TwoSumProblem.java) | Find pair indices summing to target using HashMap (O(n)) and two-pointers for sorted array. | Complement lookup `target - num`, hash lookup vs brute-force $O(n^2)$. |
| [`MergeSortedArrays.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/arrays/MergeSortedArrays.java) | Merge two sorted arrays in O(m+n) time without calling `Arrays.sort()`. | Merge step of MergeSort, two-pointer synchronization, in-place merge. |
| [`ReverseArrayInPlace.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/arrays/ReverseArrayInPlace.java) | Reverse array in-place and rotate array right by K positions in 3 steps (LeetCode 189). | Reversal algorithm: reverse all, reverse first k, reverse remaining. |

---

### 3. Number Theory & Math (`com.automation.qa.numbers`)

| File | Problem Statement & Concepts | Primary Interview Focus |
| :--- | :--- | :--- |
| [`PrimeNumberCheck.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/numbers/PrimeNumberCheck.java) | Prime check in $O(\sqrt{n})$ and Sieve of Eratosthenes up to N. | Math boundary optimization, divisors of form $6k \pm 1$. |
| [`FibonacciSeries.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/numbers/FibonacciSeries.java) | Iterative generation, Nth Fibonacci calculation, and recursion with memoization. | Avoiding call stack overflow, iterative O(n) vs naive recursive $O(2^n)$. |
| [`PalindromeNumber.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/numbers/PalindromeNumber.java) | Check integer palindrome **without converting to String** (LeetCode 9). | Modulo `% 10` and division `/ 10`, half-reversal to prevent overflow. |
| [`ArmstrongNumber.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/numbers/ArmstrongNumber.java) | Narcissistic number check (153, 370, 9474) and range search. | Power calculations, digit extraction, mathematical properties. |
| [`FactorialCalculation.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/numbers/FactorialCalculation.java) | Iterative, recursive, and `BigInteger` for large factorials ($n > 20$). | Handling 32-bit `int` and 64-bit `long` arithmetic overflow in Java. |
| [`SwapNumbersWithoutTemp.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/numbers/SwapNumbersWithoutTemp.java) | Swap two numbers without 3rd variable using arithmetic (+ / -) and bitwise XOR. | Explaining integer overflow risks of addition vs safety of XOR. |

---

### 4. Collections Framework (`com.automation.qa.collections`)

| File | Problem Statement & Concepts | Primary Interview Focus |
| :--- | :--- | :--- |
| [`MapIterationAndSorting.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/collections/MapIterationAndSorting.java) | 4 ways to iterate Maps, sort by Keys (TreeMap), and **sort by Values (Streams)**. | `entrySet()`, `Map.Entry.comparingByValue()`, LinkedHashMap. |
| [`WordFrequencyCount.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/collections/WordFrequencyCount.java) | Count word frequencies in text/logs, extract duplicate words. | String sanitation, tokenization, Map frequency counts. |
| [`ListIntersectionAndDuplicates.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/collections/ListIntersectionAndDuplicates.java) | Common elements (Intersection), Expected vs Actual test dataset verification. | `removeAll()`, Set lookup, `Arrays.asList()` vs mutable lists. |
| [`ComparableVsComparator.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/collections/ComparableVsComparator.java) | `Comparable` vs `Comparator` demonstrated on test case priority & duration sorting. | `compareTo()` vs `compare()`, chained multi-field sorting. |

---

### 5. Java 8+ Streams for Automation (`com.automation.qa.streams`)

| File | Problem Statement & Concepts | Primary Interview Focus |
| :--- | :--- | :--- |
| [`Java8StreamsForQA.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/streams/Java8StreamsForQA.java) | Filter failed tests, map test names, group by status/suite, calculate avg/total duration. | `filter`, `map`, `Collectors.groupingBy()`, `DoubleSummaryStatistics`, `anyMatch`. |

---

### 6. SDET Architecture & Patterns (`com.automation.qa.sdetpatterns`)

| File | Problem Statement & Concepts | Primary Interview Focus |
| :--- | :--- | :--- |
| [`RetryMechanism.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/sdetpatterns/RetryMechanism.java) | Custom generic retry runner (`Supplier<T>`) with configurable max retries and delays. | Flaky test handling, functional interfaces, exponential backoff. |
| [`SingletonWebDriverDemo.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/sdetpatterns/SingletonWebDriverDemo.java) | Thread-safe Double-Checked Locking Singleton & `ThreadLocal` driver manager. | Parallel test execution safety, memory leak prevention via `ThreadLocal.remove()`. |
| [`RegexForQAValidation.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/sdetpatterns/RegexForQAValidation.java) | Extract dynamic Order IDs, 6-digit OTP codes, and validate Email/IPv4 formats. | `Pattern`, `Matcher`, capturing groups `matcher.group(1)`, test assertions. |
| [`TimestampAndDateUtils.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/sdetpatterns/TimestampAndDateUtils.java) | Unique test email generator, failure screenshot filename, dynamic check-in/out dates. | Modern `java.time` API (`LocalDateTime`, `DateTimeFormatter`, `LocalDate`). |
| [`ConfigFileReader.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/sdetpatterns/ConfigFileReader.java) | Classpath properties loader (`src/main/resources/config.properties`) with defaults. | `Properties`, classpath resource streams, type-safe config parsing. |
| [`CustomExceptionDemo.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/sdetpatterns/CustomExceptionDemo.java) | Custom unchecked exceptions (`ElementWaitTimeoutException`) with root-cause chaining. | Checked vs unchecked exceptions in frameworks, exception chaining. |

---

### 7. Popular Selenium WebDriver Solutions (`com.automation.qa.selenium`)
Most heavily asked real-time browser automation scenarios in technical interviews.

| File | Problem Statement & Concepts | Primary Interview Focus |
| :--- | :--- | :--- |
| [`DynamicWebTableHandler.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/selenium/DynamicWebTableHandler.java) | Parse HTML table into `List<Map<String, String>>`, click row action buttons, handle pagination. | Relative XPath axes (`following-sibling`, `ancestor`), dynamic rows/columns. |
| [`SeleniumWaitsDeepDive.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/selenium/SeleniumWaitsDeepDive.java) | Implicit vs Explicit (`WebDriverWait`) vs Fluent Wait; wait for complete page/DOM load. | Why never mix implicit & explicit waits, custom polling & exception ignoring. |
| [`WindowAndFrameHandles.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/selenium/WindowAndFrameHandles.java) | Switching tabs/windows with `getWindowHandles()`, switching to nested iframes. | `close()` vs `quit()`, switching back to parent window, `defaultContent()` vs `parentFrame()`. |
| [`JavaScriptExecutorAndActions.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/selenium/JavaScriptExecutorAndActions.java) | Mouse hover, right click, double click, drag & drop; JS clicks, scrolling & element highlighting. | Handling `ElementClickInterceptedException`, user gestures with `.perform()`. |
| [`DropdownAndShadowDomHandler.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/selenium/DropdownAndShadowDomHandler.java) | Standard `<select>` tags via `Select` class, custom Bootstrap dropdowns, Selenium 4 Shadow DOM. | `selectByVisibleText()`, modern non-select dropdowns, `getShadowRoot()`. |
| [`StaleElementHandlingStrategies.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/selenium/StaleElementHandlingStrategies.java) | 4 strategies to solve `StaleElementReferenceException` (re-querying, retry loops, `ExpectedConditions.refreshed`). | DOM node detachment causes, AJAX re-renders, avoiding `@CacheLookup` on dynamic nodes. |
| [`BrokenLinksAndImagesChecker.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/selenium/BrokenLinksAndImagesChecker.java) | Find all links (`<a>`) & images (`<img>`), verify HTTP status codes via `HttpURLConnection` in parallel. | HTTP HEAD requests, filtering javascript/mailto links, `parallelStream()`. |
| [`ScreenshotAndAlertsHandler.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/selenium/ScreenshotAndAlertsHandler.java) | JavaScript Simple/Confirm/Prompt alerts, viewport screenshot, Selenium 4 WebElement screenshot. | `alert.accept()`, `alert.dismiss()`, `alert.sendKeys()`, `element.getScreenshotAs()`. |
| [`WebDriverFactory.java`](file:///Users/yuvraj/Documents/Java/src/main/java/com/automation/qa/selenium/WebDriverFactory.java) | Clean factory for initializing headless or headed ChromeDriver with production options. | Chrome for Testing resolution, `--headless=new`, sandbox flags, safe quit. |

---

## 🌐 Real Website Selenium Automation Suite

All Selenium scenarios are executed against the industry-standard live automation sandbox: **`https://the-internet.herokuapp.com`**.

Dedicated test suite: [`SeleniumRealWebsiteTest.java`](file:///Users/yuvraj/Documents/Java/src/test/java/com/automation/qa/SeleniumRealWebsiteTest.java)

| # | Live Website Scenario | URL Tested | Key Assertions / Verifications |
| :- | :--- | :--- | :--- |
| 1 | **Page Navigation & Title** | `/` | Validates page title `"The Internet"` and `<h1>` header text. |
| 2 | **Dynamic Loading & Explicit Wait** | `/dynamic_loading/1` | Clicks Start, waits with `WebDriverWait` for `#finish` (`"Hello World!"`). |
| 3 | **Dynamic Web Table Parsing** | `/tables` | Parses HTML rows into `List<Map>`, asserts John Smith's email & due amount. |
| 4 | **Dropdown Selection** | `/dropdown` | Interacts via `Select` class, selects `"Option 2"`, asserts selected text. |
| 5 | **Broken Images Detection** | `/broken_images` | Uses JS `naturalWidth === 0` to identify client-side broken images. |
| 6 | **JavaScript Alerts Handling** | `/javascript_alerts` | Clicks trigger, switches to alert via `switchTo().alert()`, accepts, asserts result. |
| 7 | **Mouse Hover (Actions Class)** | `/hovers` | Hovers over avatar with `Actions.moveToElement()`, verifies user profile caption. |
| 8 | **Multiple Windows & Tabs** | `/windows` | Opens child tab, switches window handle, validates content, returns to parent. |

---

## 🧪 Automated Verification Suite

Run either test suite directly in **IntelliJ IDEA**, **Eclipse**, or **VS Code / Antigravity IDE**:

### 1. Full Interview Suite (Java Algorithms + Real Selenium Tests)
File: [`InterviewProgramsTest.java`](file:///Users/yuvraj/Documents/Java/src/test/java/com/automation/qa/InterviewProgramsTest.java)

```bash
# Standalone Main Runner (runs 20 tests with visible Chrome browser):
Right-click InterviewProgramsTest.java -> Run 'InterviewProgramsTest.main()'
```

### 2. Dedicated Live Website Selenium Suite
File: [`SeleniumRealWebsiteTest.java`](file:///Users/yuvraj/Documents/Java/src/test/java/com/automation/qa/SeleniumRealWebsiteTest.java)

```bash
# Standalone Main Runner (runs all 8 live website scenarios in headed mode):
Right-click SeleniumRealWebsiteTest.java -> Run 'SeleniumRealWebsiteTest.main()'
```

> [!TIP]
> **Headed vs Headless Execution**:
> - **Headed Mode (Default)**: Automatically opens a visible Chrome window on your screen for visual interaction and debugging.
> - **Headless Mode**: To run silently without opening a browser window (e.g. in CI/CD pipelines), run with `-Dheadless=true`:
>   ```bash
>   java -Dheadless=true -cp ... com.automation.qa.SeleniumRealWebsiteTest
>   ```



