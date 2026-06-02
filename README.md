# 📝 Quiz Application — Java Console App

A fully interactive **console-based Quiz Application** built in **Java**, demonstrating core **Object-Oriented Programming (OOP)** concepts including Inheritance, Polymorphism, Encapsulation, Abstraction, and File Handling.

---

## 🎯 Features

- ✅ Interactive multiple-choice quiz (A/B/C/D)
- ✅ Questions loaded from an external file (`questions.txt`)
- ✅ Input validation — only accepts A, B, C, or D
- ✅ Real-time score tracking after each question
- ✅ Final result with percentage, grade, and performance message
- ✅ Play again without restarting the application
- ✅ Handles malformed question lines gracefully
- ✅ Clean, formatted console UI with box-drawing characters

---

## 🏗️ System Design

```
┌──────────────────────────────────────────────────┐
│                  System Design                    │
├──────────────────────────────────────────────────┤
│                                                  │
│   1. Class: Question                             │
│      └── questionText, option1-4, correctAns     │
│                                                  │
│   2. Class: User (Base)                          │
│      └── name                                    │
│                                                  │
│   3. Class: Player (Derived from User)           │
│      └── score                                   │
│                                                  │
│   4. Class: Quiz                                 │
│      ├── loadQuestions()                          │
│      ├── startQuiz()                             │
│      └── displayResult()                         │
│                                                  │
│   5. File Handling                               │
│      └── questions.txt (pipe-delimited)          │
│                                                  │
└──────────────────────────────────────────────────┘
```

---

## 🧩 OOP Concepts Demonstrated

| Concept | Implementation |
|---------|---------------|
| **Encapsulation** | `Question` class — private fields with public getters/setters |
| **Inheritance** | `Player extends User` — inherits `name` attribute |
| **Polymorphism** | `Player` overrides `displayInfo()` from `User` |
| **Abstraction** | `Quiz` class hides complexity behind clean method interfaces |
| **File I/O** | `loadQuestions()` reads from `questions.txt` using `BufferedReader` |

---

## 📂 Project Structure

```
quiz-application-java/
│
├── questions.txt                ← Question bank (pipe-delimited)
│
└── src/
    ├── Main.java                ← Entry point
    │
    ├── model/
    │   ├── Question.java        ← Question model (6 attributes)
    │   ├── User.java            ← Base class (name)
    │   └── Player.java          ← Derived class (score)
    │
    └── service/
        └── Quiz.java            ← Quiz engine (load, start, result)
```

---

## 🚀 How to Run

### Prerequisites
- **Java JDK 8+** installed ([Download JDK](https://adoptium.net))

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/<your-username>/quiz-application-java.git
cd quiz-application-java

# 2. Compile
javac -d out src/Main.java src/model/Question.java src/model/User.java src/model/Player.java src/service/Quiz.java

# 3. Run
java -cp out Main
```

> **Note:** Make sure to run the `java` command from the project root directory (where `questions.txt` is located).

---

## 🖥️ Sample Output

```
╔══════════════════════════════════════════════════════╗
║            📝  QUIZ APPLICATION v1.0  📝             ║
╚══════════════════════════════════════════════════════╝

   Enter your name: John

✅ Successfully loaded 15 question(s) from 'questions.txt'.

╔══════════════════════════════════════════════════════╗
║              🎯  QUIZ STARTED  🎯                   ║
║  Player: John                                      ║
║  Total Questions: 15                               ║
╚══════════════════════════════════════════════════════╝

╔══════════════════════════════════════════════════════╗
║  Question 1                                         ║
╠══════════════════════════════════════════════════════╣
║  What is the size of int in Java?
╠──────────────────────────────────────────────────────╣
║  A) 2 bytes
║  B) 4 bytes
║  C) 8 bytes
║  D) Depends on OS
╚══════════════════════════════════════════════════════╝

   ➤ Your answer (A/B/C/D): B
   ✅ Correct! Well done! 🎉

╔══════════════════════════════════════════════════════╗
║               📊  QUIZ RESULTS  📊                  ║
╠══════════════════════════════════════════════════════╣
║  Player      : John                                 ║
║  Score       : 13 / 15                              ║
║  Percentage  : 86.7%                                ║
║  Grade       : A  (Excellent)                       ║
╠══════════════════════════════════════════════════════╣
║  🌟  Excellent work! Keep it up!                     ║
╚══════════════════════════════════════════════════════╝
```

---

## 📄 Question File Format

Questions are stored in `questions.txt` using a **pipe-delimited** format:

```
questionText|option1|option2|option3|option4|correctAnswer
```

### Example:
```
What is the capital of France?|Berlin|Madrid|Paris|Rome|C
```

### Rules:
- Each line = one question
- Fields separated by `|` (pipe)
- `correctAnswer` must be **A**, **B**, **C**, or **D**
- Lines starting with `#` are treated as comments
- Empty lines are skipped
- Malformed lines are skipped with a warning

### Adding New Questions:
Simply open `questions.txt` and add new lines following the format above. No code changes needed!

---

## 🛠️ Tech Stack

- **Language:** Java (JDK 8+)
- **Paradigm:** Object-Oriented Programming
- **I/O:** BufferedReader / FileReader (File Handling)
- **UI:** Console-based with Unicode box-drawing characters
- **IDE:** VS Code with Extension Pack for Java

---

## 📊 Class Diagram

```
        ┌────────────────────┐
        │      User          │  ← Base Class
        │────────────────────│
        │ # name: String     │
        │────────────────────│
        │ + displayInfo()    │
        └────────┬───────────┘
                 │ extends
                 ▼
        ┌────────────────────┐
        │     Player         │  ← Derived Class
        │────────────────────│
        │ - score: int       │
        │────────────────────│
        │ + incrementScore() │
        │ + resetScore()     │
        │ + displayInfo()    │  ← Overridden (Polymorphism)
        └────────────────────┘

  ┌─────────────────────────────┐       ┌────────────────────────┐
  │         Question            │       │        Quiz            │
  │─────────────────────────────│       │────────────────────────│
  │ - questionText: String      │       │ - questions: List      │
  │ - option1: String           │◄──────│ - player: Player       │
  │ - option2: String           │       │ - filePath: String     │
  │ - option3: String           │       │ - scanner: Scanner     │
  │ - option4: String           │       │────────────────────────│
  │ - correctAns: String        │       │ + loadQuestions()      │
  │─────────────────────────────│       │ + startQuiz()          │
  │ + displayQuestion()         │       │ + displayResult()      │
  │ + isCorrect()               │       └────────────────────────┘
  └─────────────────────────────┘
```

---

## 📜 License

This project is open source and available under the [MIT License](LICENSE).

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-questions`)
3. Commit your changes (`git commit -m 'Add new question category'`)
4. Push to the branch (`git push origin feature/new-questions`)
5. Open a Pull Request

---

## ✍️ Author

Built as a demonstration of **Object-Oriented Programming** concepts in Java.

---

> ⭐ If you found this project helpful, please give it a star!
