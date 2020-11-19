package practice;

import common.ListNode;

public class Study {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode start = pre, end = pre;
        while (n != 0) {
            start = start.next;
            n--;
        }
        while (start.next != null) {
            start = start.next;
            end = end.next;
        }
        end.next = end.next.next;
        return pre.next;
    }

    public static void main(String[] args) {
      /*  ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        ListNode f = new ListNode(6);
        ListNode g = new ListNode(7);
        g.next = null;
        f.next = g;
        e.next = f;
        d.next = e;
        c.next = d;
        b.next = c;
        a.next = b;
        System.out.println(a);
        ListNode listNode = removeNthFromEnd(a, 6);
        System.out.println(listNode);*/
        ListNode head = new ListNode(7);
        ListNode node = new ListNode(-1);
        ListNode temp = head;
        ListNode next = null;
        while (temp != null) {
            next = temp.next;
            temp.next = node.next;
            node.next = temp;
            temp = next;
        }

    }


}
