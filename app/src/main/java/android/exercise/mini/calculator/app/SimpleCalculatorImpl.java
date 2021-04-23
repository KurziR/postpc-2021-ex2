package android.exercise.mini.calculator.app;

import java.io.Serializable;
import java.util.ArrayList;

public class SimpleCalculatorImpl implements SimpleCalculator {

  // todo: add fields as needed
  ArrayList<String> calc = new ArrayList<>();
  private int result = 0;
  private char sign = '+';

  @Override
  public String output() {
    // todo: return output based on the current state
    if (calc.isEmpty()) {
      return "0";
    }
    return calc.toString();
  }

  @Override
  public void insertDigit(int digit) {
    if (digit >= 0 && digit <= 9) {
      calc.add("digit");
      if (sign == '+') {
        result += digit;
      }
      else {
        result -= digit;
      }
    }
    else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void insertPlus() {
    calc.add("+");
    sign = '+';
  }

  @Override
  public void insertMinus() {
    calc.add("-");
    sign = '-';
  }

  @Override
  public void insertEquals() {
    // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
    //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
    calc.clear();
    String resultAsString = Integer.toString(result);
    calc.add(resultAsString);
  }

  @Override
  public void deleteLast() {
    // todo: delete the last input (digit, plus or minus)
    //  e.g.
    //  if input was "12+3" and called `deleteLast()`, then delete the "3"
    //  if input was "12+" and called `deleteLast()`, then delete the "+"
    //  if no input was given, then there is nothing to do here
    if (calc.isEmpty()) {
      return;
    }
    calc.remove(0);
  }

  @Override
  public void clear() {
    // todo: clear everything (same as no-input was never given)
    calc.clear();
    result = 0;
  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    // todo: insert all data to the state, so in the future we can load from this state
    state.calc = new ArrayList<>(calc);
    state.result = result;
    state.sign = sign;
    return state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    // todo: use the CalculatorState to load
    this.calc = casted.calc;
    this.result = casted.result;
    this.sign = casted.sign;
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
    ArrayList<String> calc;
    private int result = 0;
    private char sign = '+';
  }
}
