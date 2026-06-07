package com.example.naikkuy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naikkuy.R;
import com.example.naikkuy.model.BookingModel;
import com.example.naikkuy.util.FormatHelper;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
    public interface OnDeleteClickListener {
        void onDeleteClick(BookingModel booking);
    }

    private final ArrayList<BookingModel> bookingList;
    private final OnDeleteClickListener listener;

    public BookingAdapter(ArrayList<BookingModel> bookingList, OnDeleteClickListener listener) {
        this.bookingList = bookingList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        BookingModel booking = bookingList.get(position);
        holder.txtPassenger.setText(booking.getPassengerName());
        holder.txtBookingInfo.setText(booking.getTransport() + " - " + booking.getDestination() + " • " + booking.getDate());
        holder.txtBookingTotal.setText(booking.getTicketCount() + " tiket • " + FormatHelper.rupiah(booking.getTotalPrice()));
        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(booking));
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView txtPassenger, txtBookingInfo, txtBookingTotal;
        Button btnDelete;

        BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPassenger = itemView.findViewById(R.id.txtPassenger);
            txtBookingInfo = itemView.findViewById(R.id.txtBookingInfo);
            txtBookingTotal = itemView.findViewById(R.id.txtBookingTotal);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
