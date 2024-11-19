
/**
Juan David Calle Correa
**/

package com.mycompany.listmanager;

// Es una clase que nos permite obtener la entrada de datos primitivos
import java.util.Scanner;

// Implementa la lógica para trabajar con listas genéricas
import java.util.LinkedList;

// Nos permite determinar fácil y rápidamente si un objeto ya está en el conjunto o no
import java.util.HashSet;

// Es una colección que no permite elementos repetidos
import java.util.Set;

// Trabaja sobre los objetos que implementen la interfaz Collection sin importar su backend
import java.util.Collections;

public class ListManager {

    // Clase Nodo para la lista enlazada simple
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Clase para la lista enlazada simple
    static class SimpleLinkedList {
        Node head;

        // Método para agregar elementos a la lista
        void add(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
            } else {
                Node temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newNode;
            }
        }

        // Método para imprimir la lista
        void printList() {
            Node temp = head;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }
    }

    // Clase Nodo para la lista circular
    static class CircularNode {
        int data;
        CircularNode next;
        CircularNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Clase para la lista circular
    static class CircularLinkedList {
        CircularNode head;

        // Método para agregar elementos a la lista
        void add(int data) {
            CircularNode newNode = new CircularNode(data);
            if (head == null) {
                head = newNode;
                head.next = head;
            } else {
                CircularNode temp = head;
                while (temp.next != head) {
                    temp = temp.next;
                }
                temp.next = newNode;
                newNode.next = head;
            }
        }

        // Método para imprimir la lista
        void printList() {
            if (head != null) {
                CircularNode temp = head;
                do {
                    System.out.print(temp.data + " ");
                    temp = temp.next;
                } while (temp != head);
                System.out.println();
            }
        }
    }

    // Clase Nodo para la lista doblemente enlazada
    static class DoubleNode {
        int data;
        DoubleNode next;
        DoubleNode prev;
        DoubleNode(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    // Clase para la lista doblemente enlazada
    static class DoublyLinkedList {
        DoubleNode head;

        // Método para agregar elementos a la lista
        void add(int data) {
            DoubleNode newNode = new DoubleNode(data);
            if (head == null) {
                head = newNode;
            } else {
                DoubleNode temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newNode;
                newNode.prev = temp;
            }
        }

        // Método para imprimir la lista
        void printList() {
            DoubleNode temp = head;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }

        // Método para ordenar la lista
        void sortList() {
            if (head == null) return;
            LinkedList<Integer> list = new LinkedList<>();
            DoubleNode temp = head;
            while (temp != null) {
                list.add(temp.data);
                temp = temp.next;
            }
            Collections.sort(list);
            temp = head;
            for (int data : list) {
                temp.data = data;
                temp = temp.next;
            }
        }

        // Método para eliminar duplicados
        void removeDuplicates() {
            if (head == null) return;
            Set<Integer> seen = new HashSet<>();
            DoubleNode temp = head;
            DoubleNode prev = null;
            while (temp != null) {
                if (seen.contains(temp.data)) {
                    prev.next = temp.next;
                    if (temp.next != null) {
                        temp.next.prev = prev;
                    }
                } else {
                    seen.add(temp.data);
                    prev = temp;
                }
                temp = temp.next;
            }
        }

        // Método para filtrar números primos
        DoublyLinkedList filterPrimes(int max) {
            DoublyLinkedList primeList = new DoublyLinkedList();
            DoubleNode temp = head;
            while (temp != null) {
                if (isPrime(temp.data) && temp.data <= max) {
                    primeList.add(temp.data);
                }
                temp = temp.next;
            }
            return primeList;
        }

        // Método para verificar si un número es primo
        boolean isPrime(int num) {
            if (num <= 1) return false;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        // Crear y llenar la lista enlazada simple
        try (Scanner scanner = new Scanner(System.in)) {
            // Crear y llenar la lista enlazada simple
            SimpleLinkedList simpleList = new SimpleLinkedList();
            System.out.println("Ingrese elementos para la lista enlazada simple (terminar con -1):");
            int input;
            while ((input = scanner.nextInt()) != -1) {
                simpleList.add(input);
            }
            System.out.println("Lista enlazada simple:");
            simpleList.printList();
            
            // Crear y llenar la lista circular
            CircularLinkedList circularList = new CircularLinkedList();
            System.out.println("Ingrese elementos para la lista circular (terminar con -1):");
            while ((input = scanner.nextInt()) != -1) {
                circularList.add(input);
            }
            System.out.println("Lista circular:");
            circularList.printList();
            
            // Crear y llenar la lista doblemente enlazada
            DoublyLinkedList doublyList = new DoublyLinkedList();
            System.out.println("Agregando elementos de las listas anteriores a la lista doblemente enlazada...");
            // Agregar elementos de la lista simple
            Node tempSimple = simpleList.head;
            while (tempSimple != null) {
                doublyList.add(tempSimple.data);
                tempSimple = tempSimple.next;
            }
            // Agregar elementos de la lista circular
            if (circularList.head != null) {
                CircularNode tempCircular = circularList.head;
                do {
                    doublyList.add(tempCircular.data);
                    tempCircular = tempCircular.next;
                } while (tempCircular != circularList.head);
            }
            System.out.println("Lista doblemente enlazada:");
            doublyList.printList();
            
            // Ordenar y eliminar duplicados
            System.out.println("Ordenando y eliminando duplicados de la lista doblemente enlazada...");
            doublyList.sortList();
            doublyList.removeDuplicates();
            System.out.println("Lista doblemente enlazada ordenada y sin duplicados:");
            doublyList.printList();
            
            // Filtrar números primos
            System.out.println("Ingrese un numero para filtrar los numeros primos menores o iguales a ese numero:");
            int maxPrime = scanner.nextInt();
            DoublyLinkedList primeList = doublyList.filterPrimes(maxPrime);
            System.out.println("Lista de numeros primos filtrados:");
            primeList.printList();
        }
    }
}
