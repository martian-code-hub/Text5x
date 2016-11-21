package com.example.martian.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martian.bean.Node;
import com.example.martian.text5x.R;

import java.util.LinkedList;

/**
 * Created by Administrator on 2016/7/6.
 */
public class ArithmeticActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private String mTitle;

    private Toolbar toolbar;

    private TextView iv_2 ,iv_4;


    private int[] datasOld;

    private  LinkedList<Node> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        int flag = getIntent().getExtras().getInt("flag");
        mTitle = getIntent().getExtras().getString("title");
        switch (flag) {
            case 0:
                getWindow().setEnterTransition(new Explode());
                break;
            case 1:
                getWindow().setEnterTransition(new Slide());
                break;
            case 2:
                getWindow().setEnterTransition(new Fade());
                break;
        }

        setContentView(R.layout.activity_arithmetic);
        iniView();
    }


    private void iniView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_toolbar);
        toolbar.setBackgroundColor(this.getResources().getColor(R.color.colorPrimaryDark));
//        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setTitle(mTitle);
//        toolbar.setSubtitle("toolbar");
//        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return false;
            }
        });
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                createNotifition();
//            }
//        });
        //排序
        datasOld = getResources().getIntArray(R.array.arithmetic_array);
        TextView tv_1 = (TextView) findViewById(R.id.activity_arithmetic_tv_1);
        tv_1.setText("原数据：" + arrayToString(datasOld));
        iv_2 = (TextView) findViewById(R.id.activity_arithmetic_tv_2);
        //二分法查询
        TextView tv_3 = (TextView) findViewById(R.id.activity_arithmetic_tv_3);
        tv_3.setText("数据原：" + arrayToString(arithmeticOne(datasOld)));
        iv_4 = (TextView) findViewById(R.id.activity_arithmetic_tv_4);
        //二叉树
        TextView tv_5 = (TextView) findViewById(R.id.activity_arithmetic_tv_5);
        tv_5.setText("数据原：" + arrayToString(datasOld));
    }

    /**
     * 数组转字符串
     *
     * @param data
     * @return
     */
    public String arrayToString(int[] data) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int a : data) {
            sb.append(String.valueOf(a)).append(",");
        }
        sb.deleteCharAt(sb.length() - 1).append("]");
        return sb.toString();
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.activity_arithmetic_iv_1:
                iv_2.setText("后数据：" + arrayToString(arithmeticOne(datasOld)));
                break;
            case R.id.activity_arithmetic_iv_2:
                iv_2.setText("后数据：" + arrayToString(arithmeticTwo(datasOld)));
                break;
            case R.id.activity_arithmetic_iv_3:
                iv_2.setText("后数据：" + arrayToString(arithmeticThree(datasOld)));
                break;
            case R.id.activity_arithmetic_iv_4:
                iv_2.setText("后数据：" + arrayToString(arithmeticFour(datasOld)));
                break;
            case R.id.activity_arithmetic_iv_5:
                iv_2.setText("后数据：" + arrayToString(arithmeticFive(datasOld)));
                break;
            case R.id.activity_arithmetic_iv_6:
                iv_2.setText("后数据：" + arrayToString(arithmeticSix(datasOld)));
                break;
            case R.id.activity_arithmetic_iv_7:
                break;
            case R.id.activity_arithmetic_iv_8:
                break;
            case R.id.activity_arithmetic_iv_9:
                iv_4.setText("结果："+arithmeticNine(arithmeticOne(datasOld),datasOld[(int)(Math.random()*datasOld.length)]));
                break;
            case R.id.activity_arithmetic_iv_10:
                iv_4.setText("结果："+arithmeticTen(arithmeticOne(datasOld),datasOld[(int)(Math.random()*datasOld.length)]));
                break;

            case R.id.activity_arithmetic_iv_11:
                createTree(datasOld);
                break;
            case R.id.activity_arithmetic_iv_12:
                preOrderTraverse(list.get(0));
                break;
            case R.id.activity_arithmetic_iv_13:
                inOrderTraverse(list.get(0));
                break;
            case R.id.activity_arithmetic_iv_14:
                postOrderTraverse(list.get(0));
                break;
        }

    }

    /**
     * 冒泡算法
     * 两个数比较大小，较大的数下沉，较小的数冒起来
     */
    public int[] arithmeticOne(int[] data) {
        int length = data.length;
        int temp;
        //普通的冒泡算法
//       for (int i = 0;i<length-1;i++){
//           for(int j =length-1;j>i;j-- ){
//               if(data[j]<data[j-1]){
//                   temp = data[j];
//                   data[j] = data[j-1];
//                   data[j-1] = temp;
//               }
//           }
//       }
//优化的冒泡算法
        boolean flag;
        for (int i = 0; i < length - 1; i++) {
            flag = false;
            for (int j = length - 1; j > i; j--) {
                if (data[j] < data[j - 1]) {
                    temp = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        return data;
    }

    /**
     * 选择算法
     * 在长度为N的无序数组中，第一次遍历n-1个数，找到最小的数值与第一个元素交换；
     * 第二次遍历n-2个数，找到最小的数值与第二个元素交换；
     * 。。。
     * 第n-1次遍历，找到最小的数值与第n-1个元素交换，排序完成。
     */
    public int[] arithmeticTwo(int[] data) {
        int length = data.length;

        for (int i = 0; i < length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < length; j++) {
                if (data[j] < data[index]) {
                    index = j;
                }
            }
            if (index != i) {
                int temp = data[index];
                data[index] = data[i];
                data[i] = temp;
            }
        }
        return data;
    }

    /**
     * 插入算法
     * 在要排序的一组数中，假定前n-1个数已经排好序，现在将第n个数插到前面的有序数列中，
     * 使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序。
     */
    public int[] arithmeticThree(int[] data) {
        int length = data.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (data[j] < data[j - 1]) {
                    int temp = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
        return data;
    }


    /**
     * 希尔算法
     * 在要排序的一组数中，根据某一增量分为若干子序列，并对子序列分别进行插入排序。
     * 然后逐渐将增量减小,并重复上述过程。直至增量为1,此时数据序列基本有序,最后进行插入排序。
     */
    public int[] arithmeticFour(int[] data) {
        int length = data.length;
        int increm = length;
        int temp;
        while (true) {
            increm = increm / 2;
            for (int i = 0; i < increm; i++) {//把数据分成若干的子数组
                for (int j = i + increm; j < length; j += increm) {//从子数组的第二个元素开始遍历
                    for (int k = j; k > i; k -= increm) {//子数组取出的元素与本身之前的元素遍历（插入排序）
                        if (data[k] < data[k - 1]) {
                            temp = data[k];
                            data[k] = data[k - 1];
                            data[k - 1] = temp;
                        } else {
                            break;
                        }
                    }
                }
            }
            if (increm == 1) {
                break;
            }
        }
        return data;
    }

    /**
     * 快速算法
     * 先从数列中取出一个数作为key值；
     * 将比这个数小的数全部放在它的左边，大于或等于它的数全部放在它的右边；
     * 对左右两个小数列重复第二步，直至各区间只有1个数。
     */
    public int[] arithmeticFive(int[] data) {
        quitSort(data, 0, data.length - 1);
        return data;
    }

    //递归算法
    private void quitSort(int[] data, int start, int end) {
        if (start >= end)
            return;

        int i = start;
        int j = end;
        int key = data[start];
        while (i < j) {
            while (i < j && data[j] > key)//在key值的右边的元素中从右向左找到第一个小与key的值
                j--;
            if (i < j) {
                data[i] = data[j];
                i++;
            }
            while (i < j && data[i] < key)//在key值的左边的元素中从左向右找到第一个大与key的值
                i++;
            if (i < j) {
                data[j] = data[i];
                j--;
            }
        }
        //i==j
        data[i] = key;
        quitSort(data, start, i - 1);
        quitSort(data, i + 1, end);
    }

    /**
     * 归并算法
     * 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法的一个非常典型的应用。
     * 首先考虑下如何将2个有序数列合并。这个非常简单，只要从比较2个数列的第一个数，谁小就先取谁，
     * 取了后就在对应数列中删除这个数。然后再进行比较，如果有数列为空，那直接将另一个数列的数据依次取出即可。
     */
    public int[] arithmeticSix(int[] data) {
        quitSort(data, 0, data.length - 1);
        return data;
    }

    /**
     * 二分法查找（递归）
     */
    public String arithmeticNine(int[] data,int sdata) {
           int result =  recursion(data,0,data.length-1,sdata);
        if(result>=0){
            return "sreachdata:"+sdata+"  index:"+result;
        }else{
            return "没有找到您要查询的数据";
        }
    }
    public int recursion(int[] data,int start,int end,int sdata){
        if(data == null){
            return -1;
        }
        if(start<=end){
            int mid = (start+end)/2;
            if(sdata == data[mid]){
                return mid;
            }else if(sdata<data[mid]){
                return  recursion(data,start,mid-1,sdata);
            }else if(sdata>data[mid]){
                return  recursion(data,mid+1,end,sdata);
            }
        }else{
            return -1;
        }
        return -1;
    }

    /**
     * 二分法查找（递归）
     */
    public String arithmeticTen(int[] data,int sdata) {
        int result =  cycle(data,sdata);
        if(result>=0){
            return "sreachdata:"+sdata+"  index:"+result;
        }else{
            return "没有找到您要查询的数据";
        }
    }

    public int cycle(int[] data,int sdata){
        int start = 0;
        int end = data.length;

        while (start<=end){
            int mid = (start+end)/2;
           if(sdata == data[mid]){
               return mid;
           }else if(sdata<data[mid]){
               end = mid-1;
           }else if(sdata>data[mid]){
               start = mid+1;
           }
        }
        return -1;
    }

    /**
     * 二叉树 创建
     */
    public void createTree(int[] data) {
         list = new LinkedList<Node>();

        //先把元素全部装入list中
        for (int a:data){
            list.add(new Node(a));
        }
        //然后遍历父节点，填充父节点的左右节点 (data.length/2-1)
        int length = data.length;
        int fcount = (length/2-1);
        for(int i = 0;i<=fcount;i++){
            list.get(i).left = list.get(i*2+1);//父节点的左边子节点；
            if ((i*2+2)<=(length-1))
                list.get(i).right = list.get(i*2+2);//父节点的右边子节点，（最后一个父节点有可能没有右边子节点）；
        }
    }

    /**
     * 二叉树先序遍历
     * @param node
     */
    public void preOrderTraverse(Node node){
        if(node==null){
            return;
        }
        System.out.println(node.data);
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }
    /**
     * 二叉树中序遍历
     * @param node
     */
    public void inOrderTraverse(Node node){
        if(node==null){
            return;
        }
        inOrderTraverse(node.left);
        System.out.println(node.data);
        inOrderTraverse(node.right);
    }
    /**
     * 二叉树后序遍历
     * @param node
     */
    public void postOrderTraverse(Node node){
        if(node==null){
            return;
        }
        postOrderTraverse(node.left);
        postOrderTraverse(node.right);
        System.out.println(node.data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
