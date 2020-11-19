package practice;

import common.ListNode;

public class MNTest {
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
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
        ListNode listNode = reverseBetween(a, 2, 3);
        System.out.println(listNode);
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        if (m == n) {
            return head;
        }
        ListNode pre = null;//获取第m的元素的上一个结点
        ListNode cur = head;//第m个元素

        while (m > 1) {//
            pre = cur;
            cur = cur.next;
            m--;
            n--;
        }
        ListNode start = new ListNode(-1);
        ListNode next = null;//最后记录到n结点的上一个结点
        while (n > 0) {
            next = cur.next;//记录后序结点
            cur.next = start.next;//将当前的cur结点的next指向start的头部
            start.next = cur;//将当前结点放在  start链表的头部
            cur = next;//移动到下一个
            n--;
        }
        //cur.next 不是null.说明还有后序结点。但是此时 m---n已经反转结束。将后序链表接上就行了
        if (next != null) {
            ListNode node = start;//最后记录到n结点的上一个结点
            //  start.next.next 是 tail 是n结点的上一个结点。cur是n号结点
            while (true) {
                if (node.next == null) {
                    node.next = next;
                    break;
                }
                node = node.next;
            }
        }
        //将start链表接在 pre的后面  1-2-3-4-5
        pre.next = start.next;
        return head;
    }
}
