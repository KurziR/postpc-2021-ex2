package android.exercise.mini.calculator.app;

import java.io.Serializable;
import java.util.Stack;

import static java.lang.Character.isDigit;

public class SimpleCalculatorImpl implements SimpleCalculator {

  private Stack calc = new Stack();

  // todo: add fields as needed

  @Override
  public String output() {
    // todo: return output based on the current state
    // String userInput = editTextTitle.getText().toString();
    // textViewTitle.setText(userInput);
    return "reut";
  }

  @Override
  public void insertDigit(int digit) {
    if (isDigit(digit)) {
      calc.push(digit);
    }
    else {
      throw new IllegalArgumentException();
    }

  }

  @Override
  public void insertPlus() {
    calc.push('+');
  }

  @Override
  public void insertMinus() {
    calc.push('-');
  }

  @Override
  public void insertEquals() {
    // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
    //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
    print output();
  }

  @Override
  public void deleteLast() {
    // todo: delete the last input (digit, plus or minus)
    //  e.g.
    //  if input was "12+3" and called `deleteLast()`, then delete the "3"
    //  if input was "12+" and called `deleteLast()`, then delete the "+"
    //  if no input was given, then there is nothing to do here
    if (calc.empty()) {
      return;
    }
    calc.pop();
  }

  @Override
  public void clear() {
    // todo: clear everything (same as no-input was never given)
    calc.clear();
  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    // todo: insert all data to the state, so in the future we can load from this state
    state = calc.clone();
    return state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    // todo: use the CalculatorState to load
  }

  private static class CalculatorState implements Serializable {
    /*
    TODO: add fields to this class that will store the calculator state
    all fields must only be from the types:
    - primitives (e.g. int, boolean, etc)
    - String
    - ArrayList<> where the type is a primitive or a String
    - HashMap<> where the types are primitives or a String
     */
  }
}
