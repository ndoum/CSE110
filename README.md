# Rüm8 - created by roommate Matching Experts (rME)

![rum8 logo](https://github.com/ndoum/CSE110/blob/master/app/src/main/ic_launcher-web.png)

| Contributor                                                   | Role                      |
| ---                                                           | ---                       |
| [Nia Doumbalska](https://github.com/ndoum)                    | Senior System Analyst     |
| [Tina Hsieh](https://github.com/aiiitingx)                    | Business Analyst          |
| [Alvin Li](https://github.com/alvinli222)                     | Software Architect        |
| [Hao Luo](https://github.com/hl219)                           | Software Development Lead |
| [Dorian Maldonado](https://github.com/dorianm7)               | User Interface Specialist |
| [Iris Nayki](https://github.com/inayki)                       | Project Manager           |
| [Shayan Raisi](https://github.com/shayan900)                  | Quality Assurance Lead    |
| [Chase Sriprajittichai](https://github.com/cksriprajittichai) | Algorithm Specialist      |
| [Ethan Yuan](https://github.com/Ethan-Yuan-ZY)                | Database Specialist       |
| [Cassie Yu](https://github.com/CassieYud)                     | Software Development Lead |

### Introduction
UCSD students can forgo the unknowns of randomly assinged roommates and difficult to navigate websites and take matters into their own hands with rüm8: an android app for UCSD students to find the perfect roommate. Users are guided in the profile setup process to make sure vital information is provided, and a series of lifestyle and preference questions are asked in order to only show users compatible potential roommates. When browsing through profiles, users can "link" with a user if they like their profile. If there is a mutual "link," then rüm8 sends a notification to both users (if they are logged in). From there, users can view all their mutual links in their "link list" as well as review their profile again and view previously hidden contact information. Using rüm8, students can find and reach out to their ideal roommate and make their housing experience the best it can be.

### Login credentials
1. Username: ndoumbalska@ucsd.edu
    Password: cse110isgr8

### Requirements
You should have Java 8 running in an updated Android Studio. Level 26 or higher Emulator API.

### Installation instructions
Clone this repository in Android Studio and build the application on your device or emulator in order to run the app. 

### How to run
Log in using a registered ucsd email account and corresponding password.

### Known bugs

---------------------------------------------------------------------------------------------------------

# Developer guide

### Things to remember:
- __Never create a branch off of `dev`!__ <br/>
  __Never merge `dev` into any branch!__
    - `dev` contains many changes that, in nearly all cases, do not belong on any other branch.
- Create small and specific branches.
- Constantly pull your branch's base branch to stay up to date with other people's changes.
    - You can do this by:
        1. Checking out your branch's base branch: <br/>
           `git checkout [your branch's base branch]`
        2. Pulling any new changes from the remote: <br/>
           `git pull`
        3. Returning to your branch (checkout your branch again): <br/>
           `git checkout [your branch]`
        4. Merging your branch's base branch _into_ your branch: <br/>
           `git merge [your branch's base branch]`
- Test your code on the `dev` branch before creating a pull request to merge your code into your branch's base branch.
    - `dev` will constantly be pulling from `master`, so testing your code on `dev` should prevent bugs from getting on `master`.
    - Once you have tested your code on the `dev` branch, create a pull request (PR) to merge your branch into its base branch.
        - Who and how many people you send it to should depend on the significance of your code.
        - Write a short (1-4 sentences) description of your PR.
        - You can send your PRs to other people through Slack.

---------------------------------------------------------------------------------------------------------

### Branches:
- What is a branch in git?
    - A branch is _like_ a copy of the repository from a specific moment in time (a specific commit).
    - Learn more: https://git-scm.com/book/en/v1/Git-Branching-What-a-Branch-Is
- What is the purpose of a branch?
    - Branches make collaboration organized and simple. By using branches, multiple people can work independently on related code.
    - Branches allow us to easily keep track of changes that we make.
- When should I create a branch?
    - Create a branch for any change that you want to make.
    - Create branches that will have a short lifespan that are used for a single particular feature or specific focus.
        - Small and specific branches are much easier to maintain, easier to merge, and easier to review.
        - Learn more: https://git-scm.com/book/en/v2/Git-Branching-Branching-Workflows
            - Read the "Topic Branches" section.

### Development workflow and branch lifecycle:
1. You want to make a change to the codebase.
2. Decide which branch you want to contribute to. This branch is going to be the base branch of the branch that you are about to create.
    - The base branch is the branch that you want to create your branch off of.
    - The base branch is the branch that you will later merge (or create a PR to merge) your branch into.
    - If the code you are writing is only dependent on the code in `master`, then `master` is your base branch. Otherwise, if the code you are writing is dependent on code that is not in `master`, then your branch's base branch is going to be the branch that contains the code that your branch depends on.
    - `dev` should never be your base branch. `dev` contains many changes that, in nearly all cases, do not belong on any other branch.
3. Create a new branch off of the base branch.
4. Make changes to your branch.
5. Finish making changes to your branch.
6. If possible, merge your branch into `dev` and ensure that everything works.
    - You do not need to create a PR to do this.
    - If you run into merge conflicts, _carefully_ resolve them. If you are unsure how to resolve them, ask someone else for help. Do not resolve merge conflicts if you do not understand how to resolve merge conflicts or if you do not understand the code that you are deleting.
7. Merge your branch into your branch's base branch.
    - Depending on what your branch's base branch is, you may or may not need to create a PR to merge your branch into its base branch.
        - If you want to merge your branch into `master`, you must create a PR to do this.
    - Make sure that you merge your branch _into_ its base branch:
        - Merge \<your branch> into \<your branch's base branch> <br/>
          Merge \<your branch> → \<your branch's base branch>
8. Your branch has served its purpose and it has been merged into another branch. Your branch should not be used anymore.
    - To enforce this, after branches are merged, they are deleted from the remote repository.

---------------------------------------------------------------------------------------------------------

### Activities and controllers (MVC):
- What is a controller?
    - A controller _controls all the logic of a single page in the app_.
        - Each `Activity` is controlled by one controller.
            - I.e. `RegistrationActivity` is controlled by `RegistrationController`.
  - A controller has one or multiple listeners (interface) that are listening to the actions of the controller. When the controller does something, it notifies its listeners so that they can respond accordingly.
    - I.e. `RegistrationController` has a `RegistrationControllerListener` that it notifies when it does something.
        - `RegistrationActivity implements RegistrationControllerListener` so that it will be notified when the controller does something.
- What's the point?
    - If we follow this pattern of putting all the logic of a app page in a controller, then we can separate the UI code and the logic (code).
    - If all the logic code is in the controller, then we can easily test the controller.
    - This will make our code modular, testable, and maintainable.
- Look at `RegistrationActivity`, `RegistrationController`, and `RegistrationControllerListener` to see an example of this.
- Talk to Chase about this if you'd like more explanation or a better understanding.

---------------------------------------------------------------------------------------------------------

### Git commands:
| Command                                              | Description                                             |
| ---                                                  | ---                                                     |
| `git status`                                         | Check status                                            |
| `git add [file-name.txt]`                            | Add a file to the staging area                          |
| `git add -A`                                         | Add all new and changed files to the staging area       |
| `git commit`                                         | Commit changes - must enter message in prompt           |
| `git commit -m "[commit message]"`                   | Commit changes with inline message                      |
| `git rm -r [file-name.txt]`                          | Remove a file (or folder)                               |
| `git branch`                                         | List branches (the asterisk denotes the current branch) |
| `git branch -a`                                      | List all branches (local and remote)                    |
| `git branch [branch name]`                           | Create a new branch                                     |
| `git branch -d [branch name]`                        | Delete a branch                                         |
| `git checkout -b [branch name]`                      | Create a new branch and switch to it                    |
| `git checkout [branch name]`                         | Checkout a branch (local or remote branch)              |
| `git checkout -b [branch name] origin/[branch name]` | Clone a remote branch and switch to it                  |
| `git checkout [branch name]`                         | Switch to a branch                                      |
| `git merge [branch name]`                            | Merge a branch into the active branch                   |
| `git merge [source branch] [target branch]`          | Merge a branch into a target branch                     |
| `git stash`                                          | Stash changes in a dirty working directory              |
| `git push`                                           | Push changes to remote repository                       |
| `git pull`                                           | Update local repository to the newest commit            |
| `git log`                                            | View commit history                                     |
| `git log --summary`                                  | View detailed commit history                            |
| `git diff [source branch] [target branch]`           | Show diff of two branches                               |
