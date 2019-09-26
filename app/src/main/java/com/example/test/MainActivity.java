package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private Button okButton;
    private ClearEditText et;
    private Adapter adapter;
    private ArrayList<ItemClass> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        okButton = (Button) findViewById(R.id.okButton);

        okButton.setOnClickListener(this);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        arrayList = new ArrayList<>();
        adapter = new Adapter(arrayList);

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
        });
        //기본 구분선
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.okButton:
                et = findViewById(R.id.et);
                String strEt = et.getText().toString();

                if (strEt.equals("")) {
                    Toast.makeText(this, "값을 입력해주세요", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                } else {
                    ItemClass item = new ItemClass(strEt);
                    arrayList.add(item);
                    adapter.notifyDataSetChanged();
                }
                break;

            case R.id.deleteButton:
                break;

        }
    }

//    private class RecyclerDecoration extends RecyclerView.ItemDecoration {
//        private final int mTop;
//
//
//
//        public RecyclerDecoration( int mTop) {
//            this.mTop = mTop;
//
//        }
//
//        @Override
//        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//            super.getItemOffsets(outRect, view, parent, state);
//            if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1)
//
//
//                outRect.top = mTop;
//
//
//        }
//
//    }
}
