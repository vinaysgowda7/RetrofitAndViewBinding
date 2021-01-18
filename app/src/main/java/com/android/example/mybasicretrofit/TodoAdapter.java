package com.android.example.mybasicretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.android.example.mybasicretrofit.databinding.ItemTodoBinding;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter  extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private ItemTodoBinding itemTodoBinding;
    private List<Todo> todoList = new ArrayList<>();
    private Context mContext;

    public TodoAdapter(List<Todo> todoList,Context context){
        this.todoList = todoList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public TodoAdapter.TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        itemTodoBinding = ItemTodoBinding.inflate(inflater,parent,false);
        return new TodoViewHolder(itemTodoBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.TodoViewHolder holder, int position) {
        Todo todo = todoList.get(position);
        holder.itemTodoBinding.tvTitle.setText(todo.getTitle());
        holder.itemTodoBinding.cbDone.setChecked(todo.getCompleted());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder{

        private static ItemTodoBinding itemTodoBinding;
        // adapter item.xml binding to ViewHolder
        public TodoViewHolder( ItemTodoBinding itemView) {
            super(itemView.getRoot());
            this.itemTodoBinding = itemView;
        }
    }

}
