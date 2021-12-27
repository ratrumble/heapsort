package heapsort;

import java.util.ArrayList;
import java.util.Random;

public class heapsort {
  //MAX HEAP 
  private ArrayList<Integer> heapArray;
  
  Random numGen = new Random();
  
  public heapsort() {
    
    heapArray = new ArrayList<Integer>();
    for (int i = 0; i < 10; i++) {
      heapArray.add((int) ((Math.random() * (10))));
    }
    System.out.println("Pre-sorted Heap : " + message());
  }
  
  public String message() {
    String message = "";
    for (int i = 0; i < heapArray.size(); i++) {
      message += heapArray.get(i) + " ";
    }
    return message;
  }
  
  public void heapify(){
    ArrayList<Integer> subHeapArray = deepCopy();
    heapArray.clear();
    for (int i = 0; i < subHeapArray.size(); i++) {
      insert(subHeapArray.get(i));
    }
    System.out.println("Heapified! : " + message());
  }
  
  public ArrayList<Integer> deepCopy(){
    ArrayList<Integer> subHeapArray = new ArrayList<Integer>();
    for(int i = 0; i < heapArray.size(); i++) {
      subHeapArray.add(heapArray.get(i));
    }
    return subHeapArray;
  }
  
  public void up(int childIndex) {
    if(childIndex <= 0) {
      throw new IllegalArgumentException("childIndex is at position 0");
    }
    int parentIndex = (childIndex - 1)/2;
    if(heapArray.get(childIndex)>heapArray.get(parentIndex)) {
      int substituteValue = heapArray.get(childIndex);
      heapArray.set(childIndex, heapArray.get(parentIndex));
      //sets position child to heap.getparent^^^^^^^^^
      heapArray.set(parentIndex, substituteValue);
      //sets position parent to heap.getsub(heap.getchild)^^^^^^
      if(parentIndex > 0) {
        up(parentIndex);
      }
    }
  }
  
  public void down(int parentIndex) {
    int child1 = parentIndex * 2 + 1;
    int child2 = parentIndex * 2 + 2;
    int maxIndex = child1;
    if(child1 < heapArray.size() && heapArray.get(child1)!=null) {
      int maxValue = heapArray.get(child1);
      if(child2<heapArray.size() && heapArray.get(child2)!=null) {
        if(heapArray.get(child1)<heapArray.get(child2)) {
          maxIndex = child2;
          maxValue = heapArray.get(child2);
        }
      }
      if(maxValue > heapArray.get(parentIndex)) {
        int substituteValue = heapArray.get(parentIndex);
        heapArray.set(parentIndex, heapArray.get(maxIndex));
        heapArray.set(maxIndex, substituteValue);
        down(maxIndex);
      }
    }
  }
  
  public void insert(int value) {
    if(heapArray.size()>0) {
      heapArray.add(value);
      up(heapArray.size()-1);
    }
    else {
      heapArray.add(value);
    }
    System.out.println("Added " + value);
    System.out.println("heapArray is now " + message());
  }
  
  //index will always be 0, i think
  public void remove(int index) {
    int substituteValue = heapArray.get(index);
    if(heapArray.size()>0) {
      heapArray.set(index, heapArray.get(heapArray.size()-1));
      heapArray.remove(heapArray.size()-1);
      down(index);
    }
    else {
      heapArray.remove(index);
    }
    System.out.println("Removed " + substituteValue);
    System.out.println("heapArray is now " + message());
  }
  
  public static void main (String[] args) {
    heapsort heap = new heapsort();
    heap.heapify();
    heap.remove(0);
  }
  
}
