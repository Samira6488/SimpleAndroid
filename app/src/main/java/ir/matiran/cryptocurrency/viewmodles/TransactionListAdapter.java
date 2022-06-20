package ir.matiran.cryptocurrency.viewmodles;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import ir.matiran.cryptocurrency.databinding.RecyclerviewRowBinding;
import ir.matiran.cryptocurrency.modle.Profile;


public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder> {

    private final ArrayList<Profile> DetailList;
    //hint
    private final LayoutInflater detInflater;

    public TransactionListAdapter(Context context,
                                  ArrayList<Profile> detList) {
        detInflater = LayoutInflater.from(context);
        this.DetailList = detList;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        RecyclerviewRowBinding recyclerviewrowBinding = RecyclerviewRowBinding.inflate(detInflater,
                parent, false);
        return new TransactionViewHolder(recyclerviewrowBinding, this);
    }


    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        Profile detCurrent = DetailList.get(position);
        holder.bindView(detCurrent);
    }

    @Override
    public int getItemCount() {
        return DetailList.size();
    }


    //*****************************************************************************

    public class TransactionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        RecyclerviewRowBinding recyclerviewrowBinding;
       final TransactionListAdapter mAdapter;

        public TransactionViewHolder(RecyclerviewRowBinding recyclerviewrowBinding, TransactionListAdapter adapter) {
            super(recyclerviewrowBinding.getRoot());
            this.recyclerviewrowBinding = recyclerviewrowBinding;
            this.mAdapter = adapter;
            recyclerviewrowBinding.selectedcurrencyTv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            // Use that to access the affected item in CurrencyList.
            Profile element = DetailList.get(mPosition);
            // Change the word in the mWordList.
            DetailList.set(mPosition, element);

            Context context = v.getContext();
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "  Size=" + element.size + "  Price=" + element.price + "  Side=" + element.side + "  Timestamp=" + element.timestamp);
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            context.startActivity(shareIntent);

        }

        public void bindView(Profile DetailList){
            recyclerviewrowBinding.selectedcurrencyTv.setText("  Size=" + DetailList.size + "  Price=" + DetailList.price + "  Side=" + DetailList.side + "  Timestamp=" + DetailList.timestamp);
        }

    }

}
