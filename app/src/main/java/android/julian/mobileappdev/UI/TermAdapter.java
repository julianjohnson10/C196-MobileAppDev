package android.julian.mobileappdev.UI;

import android.content.Context;
import android.content.Intent;
import android.julian.mobileappdev.Entity.Term;
import android.julian.mobileappdev.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    public TermAdapter(Context context, ArrayList<Term> mTerms){
        this.context = context;
        this.mTerms = mTerms;
    }
//    class TermViewHolder extends RecyclerView.ViewHolder{
//        private final TextView termItemView;
//
//        private TermViewHolder(View itemView){
//            super(itemView);
//            termItemView=itemView.findViewById(R.id.textView);
//            itemView.setOnClickListener(view -> {
//                int pos=getAdapterPosition();
//                final Term current=mTerms.get(pos);
//                Intent intent=new Intent(context,CourseList.class);
//                intent.putExtra("id", current.getTermID());
//                intent.putExtra("name", current.getTermName());
//                intent.putExtra("start",current.getStartDate());
//                intent.putExtra("end",current.getEndDate());
//                context.startActivity(intent);
//            });
//        }
//    }
public static class TermViewHolder extends RecyclerView.ViewHolder{

    TextView termName;
    CardView cardView;
    

    public TermViewHolder(@NonNull View itemView) {
        super(itemView);

        termName = itemView.findViewById(R.id.termName);
    }

}

    public static ArrayList<Term> mTerms;
    static Context context;

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.term_list_item2, parent, false);
        return new TermViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if(mTerms!=null){
            Term current=mTerms.get(position);
            String name=current.getTermName();
            holder.termName.setText(name);
        }
        else{
            holder.termName.setText("No terms");
        }
    }

    public void setTerms(ArrayList<Term> terms){
        mTerms=terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mTerms!=null){
            return mTerms.size();
        }
        else{
            return 0;
        }
    }
}
