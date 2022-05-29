package com.example.librarymanagement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable {
    Context context;
    ArrayList<Book> bookList, originalBookList;

    public Adapter() {
    }

    public Adapter(@NonNull Context context, ArrayList<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
        originalBookList = new ArrayList<>(bookList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bookName.setText(bookList.get(position).bookName);
        holder.author.setText("By " + bookList.get(position).author);

        byte[] bytesImage = bookList.get(position).image;
        Bitmap bitmapImage = BitmapFactory.decodeByteArray(bytesImage, 0, bytesImage.length);
        holder.imageBook.setImageBitmap(bitmapImage);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                ArrayList<Book> filteredList = new ArrayList<>();

                if(charSequence == null || charSequence.length() == 0){
                    filteredList.addAll(originalBookList);
                }
                else{
                    String newText = charSequence.toString().toLowerCase().trim();

                    for(Book bookItem : originalBookList){
                        if(bookItem.getBookName().toLowerCase().contains(newText) || bookItem.getAuthor().toLowerCase().contains(newText)){
                            filteredList.add(bookItem);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                bookList.clear();
                bookList.addAll((ArrayList) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView bookName, author;
        ImageView imageBook;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            bookName = itemView.findViewById(R.id.bookName);
            author = itemView.findViewById(R.id.author);
            imageBook = itemView.findViewById(R.id.imageBook);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, BookInformation.class);
            intent.putExtra("Book Name", bookList.get(getAdapterPosition()).bookName);
            intent.putExtra("Author", bookList.get(getAdapterPosition()).author);
            intent.putExtra("Description", bookList.get(getAdapterPosition()).description);
            intent.putExtra("Image", bookList.get(getAdapterPosition()).image);
            context.startActivity(intent);
        }
    }
}
