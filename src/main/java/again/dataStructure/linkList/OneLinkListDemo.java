package again.dataStructure.linkList;

//单链表demo
public class OneLinkListDemo {

    public static void main(String[] args) {
        OneLinkList oneLinkList=new OneLinkList();
        oneLinkList.addByOrder(new Node(2,"123"));
        oneLinkList.addByOrder(new Node(1,"997"));

        oneLinkList.list();

        System.out.println("--------------");
        Node rev = oneLinkList.getRev();
        rev.list();

    }
}


class OneLinkList{
    Node head;
    Node revHead;

    public OneLinkList() {
        this.head = new Node(0,"");
        this.revHead = new Node(0,"");
    }

    public void addLast(Node node) {
        head.addLast(node);
    }

    public void addByOrder(Node node) {
        head.addByOrder(node);
    }

    public void list() {
        head.list();
        System.out.println();

    }

    public Node findById(int i) {
        return head.findById(i);
    }

    public void del(int i) {
        head.del(i);
    }

    public int getLen() {
        return head.getLen();
    }

    public Node findByLastOrderNum(int i) {
        int lOrderNum = getLen()-(i-1);
        System.out.println(lOrderNum);
        return this.findByOrderNum(lOrderNum);
    }

    public Node findByOrderNum(int index) {
        return head.findByOrderNum(index);
    }

    public void showRevRecur() {
        head.showRevRecur(head);
    }

    public Node getRev() {
        //遍历然后放在最前端 add first
        //不管head
        Node tmp=head.next;

        while (true){
            if(tmp!=null){
                Node revTmp=tmp;
                tmp=tmp.next;
                revTmp.next=revHead.next;
                revHead.next=revTmp;
                //不能放在后面 不然会移动
                //tmp=tmp.next;
            }else{
                return revHead;
            }
        }
    }

}

class Node{
    int id;
    String name;
    Node next;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void addLast(Node node){
        Node tmp=this;
        while (tmp.next!=null){
            tmp=tmp.next;
        }

        tmp.next=node;
    }

    public void list() {
        Node tmp=this;
        while (tmp!=null ){
            //if(tmp.id!=0){
                System.out.print(tmp+"->");
            //}
            tmp=tmp.next;
        }


    }

    public void addByOrder(Node node) {
        Node tmp=this;
        //因为初始是头结点0
        while (tmp.next!=null){
            if (node.id>tmp.next.id){
                tmp=tmp.next;
            }else {
                node.next=tmp.next;
                tmp.next=node;
                return;
            }
        }
        tmp.next=node;

    }

    public Node findById(int index){
        Node tmp=this;
        while (tmp.id!=index){
          if(tmp.next!=null) {
              tmp = tmp.next;
          }else {
              return null;
          }
        }
        return tmp;
    }

    public void del(int index) {
        Node tmp=this;
        Node preTmp=this;
        while (tmp.id!=index){
            if(tmp.next!=null) {
                preTmp=tmp;
                tmp = tmp.next;
            }else {
                System.out.println("找不到该节点");
                return;
            }
        }
        preTmp.next=tmp.next;
        return;
    }

    public int getLen() {
        Node tmp=this;
        int index=0;
        while (tmp.next!=null){
            tmp=tmp.next;
            index++;
        }
        return index;
    }

    public Node findByOrderNum(int orderNum) {
        int index=0;
        Node tmp=this;
        while (true){
            if(orderNum==index){
                return tmp;
            }
            if(tmp.next!=null){
                tmp=tmp.next;
                index++;
            }else {
                return null;
            }

        }

    }

    public void showRevRecur(Node recurOne) {
        if(recurOne.next!=null){
            showRevRecur(recurOne.next);
        }
        System.out.print(recurOne+"->");
    }
}