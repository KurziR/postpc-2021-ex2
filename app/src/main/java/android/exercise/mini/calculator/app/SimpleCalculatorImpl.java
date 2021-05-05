package android.exercise.mini.calculator.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class SimpleCalculatorImpl implements SimpleCalculator {

  // todo: add fields as needed
  ArrayList<String> calc = new ArrayList<>();
  private Integer result = 0;
  private char sign = ' ';
  private boolean lastIsSign = false;
  ArrayList<Integer> bufferL = new ArrayList<>();
  ArrayList<Integer> bufferR = new ArrayList<>();

  @Override
  public String output() {
    // todo: return output based on the current state
    if (calc.isEmpty()) {
      return "0";
    }
    if (calc.size() == 1 && sign != ' ') {
      calc.add("0");
    }
    Iterator<String> itr = calc.iterator();
    String exp = "";
    while(itr.hasNext()) {
      Object element = itr.next();
      exp += element;
    }
    int last = calc.size() - 1;
    String digit = calc.get(last);
    if (digit == "-" || digit == "+") {
      return exp;
    }
    lastIsSign = false;
    return exp;
  }

  @Override
  public void insertDigit(int digit) {
    if (digit >= 0 && digit <= 9) {
      calc.add(Integer.toString(digit));
      if(bufferL.size() == 0){
        bufferL.add(digit);
      }
      else if(sign == ' '){
        bufferL.add(digit);
      }
      else {
        bufferR.add(digit);
      }

      lastIsSign = false;
    }
    else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void insertPlus() {
    if (calc.isEmpty()) {
      calc.add("0");
    }
    int last = calc.size() - 1;
    String digit = calc.get(last);
    if (digit == "-" || digit == "+") {
      lastIsSign = true;
      return;
    }
    if (!lastIsSign) {
      calc.add("+");
      lastIsSign = true;
      if(bufferL.size() !=0 && bufferR.size() !=0){
        int left = 0;
        int right = 0;
        if (result !=0 ){
          left = result;
        }
        else {
          for (Integer i : bufferL) {
            left = 10 * left + i;
          }
        }
        for (Integer i : bufferR) {
          right = 10 * right + i;
        }
        if (sign == '+') {
          result = left + right;
        }
        if (sign == '-') {
          result = left - right;
        }
        sign = '+';
        bufferR = new ArrayList<>();
      }
      if (bufferL.size() !=0 && bufferR.size() ==0){
        sign = '+';
      }
    }
  }

  @Override
  public void insertMinus() {
    if (calc.isEmpty()) {
      calc.add("0");
    }
    int last = calc.size() - 1;
    String digit = calc.get(last);
    if (digit == "-" || digit == "+") {
      lastIsSign = true;
      return;
    }
    if (!lastIsSign) {
      calc.add("-");
      lastIsSign = true;
      if(bufferL.size() !=0 && bufferR.size() !=0 ){
        int left =0;
        int right = 0;
        if (result !=0 ){
          left = result;
        }
        else {
          for (Integer i : bufferL) {
            left = 10 * left + i;
          }
        }
        for (Integer i : bufferR) {
          right = 10*right + i;
        }
        if (sign == '+') {
          result = left + right;
        }
        if (sign == '-') {
          result = left - right;
        }
        sign = '-';
        bufferR = new ArrayList<>();
      }
      if (bufferL.size() !=0 && bufferR.size() == 0 ){
        sign = '-';
      }
    }
  }

  @Override
  public void insertEquals() {
    // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
    //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
    if (bufferR.size()!=0) {
      int right = 0;
      for (Integer i : bufferR) {
        right = 10*right + i;
      }
      if(result == 0) {
        int left = 0;
        for (Integer i : bufferL) {
          left = 10*left + i;
        }
        result = left;
      }
      if (sign == '+') {
        result = result + right;
      }
      if (sign == '-') {
        result = result - right;
      }
    }
    calc.clear();
    String resultAsString = result.toString();
    calc.add(resultAsString);
    bufferL.clear();
    bufferL.add(result);
    lastIsSign = false;
    sign = ' ';
    result = 0;
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
    int last = calc.size() - 1;
    String digit = calc.get(last);
    if (digit == "-" || digit == "+") {
      sign = ' ';
      calc.remove(last);
      return;
    }
    calc.remove(last);
    if(bufferR.size() != 0){
      bufferR.remove(bufferR.size() - 1);
    }
    else if(sign != ' '){
      sign = ' ';
    }
    else if(bufferL.size() != 0){
      bufferL.remove(bufferL.size() - 1);
    }
  }

  @Override
  public void clear() {
    // todo: clear everything (same as no-input was never given)
    calc.clear();
    result = 0;
    sign = ' ';
    lastIsSign = false;
    bufferR.clear();
    bufferL.clear();
  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    // todo: insert all data to the state, so in the future we can load from this state
    state.calc = new ArrayList<>(calc);
    state.result = result;
    state.sign = sign;
    state.lastIsSign = lastIsSign;
    state.bufferL = bufferL;
    state.bufferR = bufferR;
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
    this.lastIsSign = casted.lastIsSign;
    this.bufferL = casted.bufferL;
    this.bufferR = casted.bufferR;
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
    private boolean lastIsSign = false;
    ArrayList<Integer> bufferL = new ArrayList<>();
    ArrayList<Integer> bufferR = new ArrayList<>();
  }
}
