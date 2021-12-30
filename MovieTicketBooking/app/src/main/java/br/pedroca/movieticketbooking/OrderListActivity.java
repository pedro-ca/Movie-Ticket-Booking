package br.pedroca.movieticketbooking;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderListActivity extends AppCompatActivity {
    //private AppBarConfiguration appBarConfiguration;
    //private ActivityOrderListBinding binding;
    public static OrderDao orderDao = new OrderDao();   //TODO: add OrderDAO instances by dependency injection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivityOrderListBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_order_list);

        RecyclerView orderRecycleView = findViewById(R.id.recycleViewOrders);
        List<Order> orderList = orderDao.GetAll();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderRecycleView.setLayoutManager(layoutManager);

        orderRecycleView.setAdapter(new OrderListAdapter(this, orderList));
    }

    public static void createNewOrder(Ticket ticket){
        Order order = new Order(0,ticket,1,Order.generateRandomCode());        //TODO: method count() instead of accessing orderDao.entityList.size() directly
        orderDao.Insert(order);
    }
}