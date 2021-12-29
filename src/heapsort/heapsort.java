package heapsort;

import java.util.ArrayList;
import java.util.Random;

public class heapsort {
  //MAX HEAP 
  private ArrayList<Integer> heapArray;
  
  Random numGen = new Random();
  int heapSize;
  
  public heapsort() {
    
    heapArray = new ArrayList<Integer>();
    for (int i = 0; i < 10; i++) {
      heapArray.add((int) ((Math.random() * (10))));
    }
    System.out.println("Pre-sorted Heap : " + message());
    
    heapSize = deepCopy().size();
  }
  
  public String message() {
    String message = "";
    for (int i = 0; i < heapArray.size(); i++) {
      message += heapArray.get(i) + " ";
    }
    return message;
  }
  /**
   * tried this stupid way for heapify()
   * int num = 1;
   * int count = 0;
   * while (num < array.size()){
   * num = num * 2;
   * count++;
   * }
   * int estimatedNode = count 
   * int parentIndex = (count * 2 - 1)
   */
  
  public ArrayList<Integer> heapify(ArrayList<Integer> array){
    int index = 0;
    while((index * 2 + 1) < array.size()) {
      index++;
    }
    while(index > - 1) {
      down(index, array);
      index--;
    }
    System.out.println("Heapified! : " + message());
    return array;
  }
  
  public ArrayList<Integer> deepCopy(){
    ArrayList<Integer> deepCopy = new ArrayList<Integer>();
    for (int i = 0 ; i < heapArray.size(); i ++) {
      deepCopy.add(heapArray.get(i));
    }
    return deepCopy;
  }
  
  public void up(int childIndex, ArrayList<Integer> array) {
    if(childIndex <= 0) {
      throw new IllegalArgumentException("childIndex is at position 0");
    }
    int parentIndex = (childIndex - 1)/2;
    if(array.get(childIndex)>array.get(parentIndex)) {
      int substituteValue = array.get(childIndex);
      array.set(childIndex, array.get(parentIndex));
      //sets position child to heap.getparent^^^^^^^^^
      array.set(parentIndex, substituteValue);
      //sets position parent to heap.getsub(heap.getchild)^^^^^^
      if(parentIndex > 0) {
        up(parentIndex, array);
      }
    }
  }
  
  public void down(int parentIndex, ArrayList<Integer> array) {
    int child1 = parentIndex * 2 + 1;
    int child2 = parentIndex * 2 + 2;
    int maxIndex = child1;
    if(child1 < array.size() && array.get(child1)!=null) {
      int maxValue = array.get(child1);
      if(child2<array.size() && array.get(child2)!=null) {
        if(array.get(child1)<array.get(child2)) {
          maxIndex = child2;
          maxValue = array.get(child2);
        }
      }
      if(maxValue > array.get(parentIndex)) {
        int substituteValue = array.get(parentIndex);
        array.set(parentIndex, array.get(maxIndex));
        array.set(maxIndex, substituteValue);
        down(maxIndex, array);
      }
    }
  }
  
  public void insert(int value, ArrayList<Integer> array) {
    if(array.size()>0) {
      array.add(value);
      up(array.size()-1, array);
    }
    else {
      array.add(value);
    }
    System.out.println("Added " + value);
    System.out.println("heapArray is now " + message());
  }
  
  //index will always be 0, i think
  public void remove(int index, ArrayList<Integer> array) {
    int substituteValue = array.get(index);
    if(array.size()>0) {
      array.set(index, array.get(heapSize-1));
      array.remove(heapSize-1);
      down(index, array);
    }
    else {
      array.remove(index);
    }
    System.out.println("Removed " + substituteValue);
    System.out.println("heapArray is now " + message());
  }
  
  public void downSort(int parentIndex, ArrayList<Integer> array) {
    int child1 = parentIndex * 2 + 1;
    int child2 = parentIndex * 2 + 2;
    int maxIndex = parentIndex;
    int maxValue = array.get(parentIndex);
    if(child1 < heapSize-1 && array.get(child1)!=null) {
      maxIndex = child1;
      maxValue = array.get(child1);
      if(child2<heapSize-1 && array.get(child2)!=null) {
        if(array.get(child1)<array.get(child2)) {
          maxIndex = child2;
          maxValue = array.get(child2);
        }
      }
      if(maxValue > array.get(parentIndex)) {
        int substituteValue = array.get(parentIndex);
        array.set(parentIndex, array.get(maxIndex));
        array.set(maxIndex, substituteValue);
        downSort(maxIndex, array);
      }
    }
  }
  
  public void swapDown(int index, ArrayList<Integer> array) {
    int substituteValue = array.get(index);
    int reportedValue = array.get(heapSize-1);
    if(array.size()>0) {
      array.set(index, array.get(heapSize-1));
      array.set(heapSize-1, substituteValue);
      downSort(index, array);
    }
    System.out.println("Swapped " + substituteValue + " and " + reportedValue);
    System.out.println("heapArray is now " + message());
  }
  
  public ArrayList<Integer> reverse(ArrayList<Integer> array){
    ArrayList<Integer> reverseCopy = new ArrayList<Integer>();
    for (int i = array.size()-1; i > -1 ; i --) {
      reverseCopy.add(array.get(i));
    }
    return reverseCopy;
  }
  
  public void heapSort(ArrayList<Integer> array) {
    for (int i = 0 ; i < array.size() && heapSize > 0 ; i ++) {
      swapDown(0, array);
      heapSize--;
    }
    heapArray = reverse(array);
    System.out.println("heapSorted : " + message());
    heapSize = deepCopy().size();
  }
  
  public ArrayList<Integer> heapArray(){
    return heapArray;
  }
  
  public static void main (String[] args) {
    heapsort heap = new heapsort();
    heap.heapify(heap.heapArray);
    heap.heapSort(heap.heapArray);
    heap.remove(0, heap.heapArray());
  }
  
}
