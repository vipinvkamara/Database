package com.androidexample.calllog;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CallListData extends BaseAdapter   implements OnClickListener {
    
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater=null;
    public Resources res;
    CallListValues tempValues=null;
    int i=0;
    public static int type = 1;
    public CallListData(Activity a, ArrayList d,Resources resLocal) {
        activity = a;
        data=d;
        res = resLocal;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
    	if(data.size()<=0)
    		return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public static class ViewHolder{
        public TextView text;
        public TextView textWide;
        public ImageView image;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        ViewHolder holder;
        if(convertView==null){ 
            vi = inflater.inflate(R.layout.tabitem, null); 
            holder=new ViewHolder();
            holder.text=(TextView)vi.findViewById(R.id.text);
            holder.textWide=(TextView)vi.findViewById(R.id.textWide);
            
            
            vi.setTag(holder);
        }
        else  
            holder=(ViewHolder)vi.getTag();
        if(data.size()<=0)
        {
        	holder.text.setText("No Data");
            //holder.text1.setText("No Data");
        }
        else
        {
        tempValues=null;
        tempValues = (CallListValues) data.get(position);
        
       
        //Log.i("===", position+"---"+tempValues.getImageName());
        /*
        holder.text.setText(tempValues.getScreenName());
        holder.text1.setText(tempValues.getFromPhone());
        holder.image.setTag(tempValues.getURL());
        */
        holder.text.setText(tempValues.getPhoneText());
        //holder.text1.setText("bbbbb");
        //holder.image.setTag("ada");
        //Bitmap mBitmap = BitmapFactory.decodeResource(res, res.getIdentifier("com.fedorvlasov.lazylist:drawable/"+data[position],null,null));
        //holder.image.setImageBitmap(mBitmap);
        //holder.image.setImageResource(res.getIdentifier("com.floatster.android:drawable/"+tempValues.getImageName()+"_bbl_list_"+tempValues.getBubbleColorCode(),null,null));
        //holder.image.setImageResource(res.getIdentifier("com.floatster.android:drawable/"+tempValues.getImageName(),null,null));
        vi.setOnClickListener(new OnItemClickListener(position,tempValues.getPid()));
        }
        return vi;
    }
    
    @Override
    public void onClick(View v) {
            Log.v("Animation", "=====Row button clicked");
    }
    
    private class OnItemClickListener  implements OnClickListener{           
        private int mPosition;
        private int typeL;

        OnItemClickListener(int position,int type){
        	//super(context);
                mPosition = position;
                typeL = type;
                
        }
        @Override
        public void onClick(View arg0) {
                //Log.v("Animation", "onItemClick at position" + mPosition);
        	
        	CallList.SheduleActivity.onItemClick(mPosition,typeL);
        }               
    }
    
    private class OnLongClickListener  implements OnClickListener{           
        private int mPosition;

        OnLongClickListener(int position){
        	//super(context);
                mPosition = position;
                
        }
        @Override
        public void onClick(View arg0) {
                //Log.v("Animation", "onItemClick at position" + mPosition);
        	
        	//Shedule.SheduleActivity.onItemClick(mPosition);
        }               
    }
}