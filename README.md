# Rüm8

### General coding standards:
- Create small and specific branches, preferably branched off of master.
- Constantly pull your branch's base branch to stay up to date with other people's changes.
- Test your code on the `dev` branch before creating a pull request to merge your code into `master`.
  - `dev` will constantly be pulling from `master`, so testing your code on `dev` should prevent bugs from getting on `master`.
  - Once you have tested your code on the `dev` branch, create a pull request (PR) to merge your branch into its base branch and send it other group members to be reviewed.
    - Who and how many people you send it to should depend on the significance of your code.
    - You can send your PRs to other people through Slack.
  
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
