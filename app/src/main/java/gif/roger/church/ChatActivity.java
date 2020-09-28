package gif.roger.church;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.View;

public class ChatActivity extends AppCompatActivity {
	
	
	private boolean isAppInstalled = false;
	
	private LinearLayout linear_notice;
	private LinearLayout linear_image;
	private ScrollView vscroll2;
	private LinearLayout linear_buttons;
	private LinearLayout linear5;
	private TextView textview1;
	private ImageView imageview1;
	private LinearLayout linear3;
	private EditText edit_message;
	private Button button_send;
	private Button button_clear;
	private TextView textview2;
	private ImageView imageview2;
	
	private Intent gif = new Intent();
	private Intent msg = new Intent();
	private Intent cxd = new Intent();
	private AlertDialog.Builder whatsapp;
	private ObjectAnimator oa_d = new ObjectAnimator();
	private Intent install = new Intent();
	private Intent exit_chat = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.chat);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear_notice = (LinearLayout) findViewById(R.id.linear_notice);
		linear_image = (LinearLayout) findViewById(R.id.linear_image);
		vscroll2 = (ScrollView) findViewById(R.id.vscroll2);
		linear_buttons = (LinearLayout) findViewById(R.id.linear_buttons);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		textview1 = (TextView) findViewById(R.id.textview1);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		edit_message = (EditText) findViewById(R.id.edit_message);
		button_send = (Button) findViewById(R.id.button_send);
		button_clear = (Button) findViewById(R.id.button_clear);
		textview2 = (TextView) findViewById(R.id.textview2);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		whatsapp = new AlertDialog.Builder(this);
		
		button_send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				//Check if whatsapp is installed
				boolean isAppInstalled = appInstalledOrNot("com.whatsapp-1");
				if (isAppInstalled) {
					//Send message through whatsapp
					msg.setAction(Intent.ACTION_VIEW);
					msg.setData(Uri.parse("whatsapp://send?text=".concat(edit_message.getText().toString())));
					startActivity(msg);
				}
				else {
					_error_dialog();
				}
			}
		});
		
		button_clear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				//Clear content entered in message field
				edit_message.setText("");
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				//Change image
				imageview2.setImageResource(R.drawable.gif1);
				//Open dev portfolio in play store
				gif.setAction(Intent.ACTION_VIEW);
				gif.setData(Uri.parse("https://play.google.com/store/apps/dev?id=6776131537023742340"));
				startActivity(gif);
				SketchwareUtil.showMessage(getApplicationContext(), "Opening Google Play!");
			}
		});
	}
	private void initializeLogic() {
		//Initial chat is through whatsapp
		
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		exit_chat.setClass(getApplicationContext(), MainActivity.class);
		startActivity(exit_chat);
	}
	private void _extra () {
	}
	
	private boolean appInstalledOrNot(String uri) { android.content.pm.PackageManager pm = getPackageManager(); try { pm.getPackageInfo(uri, android.content.pm.PackageManager.GET_ACTIVITIES); return true; } catch (android.content.pm.PackageManager.NameNotFoundException e) { } return false;
	}
	
	
	private void _error_dialog () {
		final AlertDialog error_dialog = new AlertDialog.Builder(ChatActivity.this).create();
		LayoutInflater inflater = getLayoutInflater();
		
		View convertView = (View) inflater.inflate(R.layout.error_dialog, null);
		error_dialog.setView(convertView);
		
		error_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  error_dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
		
		
		LinearLayout i_bg = (LinearLayout) convertView.findViewById(R.id.linear_bg);
		
		LinearLayout i_div = (LinearLayout) convertView.findViewById(R.id.linear_div);
		
		Button i_ok = (Button) convertView.findViewById(R.id.error_okay_button);
		
		Button i_cancel = (Button) convertView.findViewById(R.id.error_cancel_button);
		
		ImageView i_header = (ImageView) convertView.findViewById(R.id.img_header);
		
		TextView i_title = (TextView) convertView.findViewById(R.id.txt_title);
		
		TextView i_msg = (TextView) convertView.findViewById(R.id.txt_msg);
		
		_setBgCorners(i_bg, 8, "#FFFFFF");
		_setBgCorners(i_ok, 8, "#03A9F4");
		_setBgCorners(i_cancel, 8, "#68CFFD");
		i_header.setElevation(5);
		i_ok.setOnClickListener(new View.OnClickListener(){
			    public void onClick(View v){
				error_dialog.dismiss();
				//Open play store url
				install.setAction(Intent.ACTION_VIEW);
				install.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp"));
				startActivity(install);
			}});
		i_cancel.setOnClickListener(new View.OnClickListener(){
			    public void onClick(View v){
				error_dialog.dismiss();
			}});
		error_dialog.show();
		_bounce(i_header);
	}
	
	
	private void _setBgCorners (final View _view, final double _radius, final String _color) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(); 
		gd.setColor(Color.parseColor("#" + _color.replace("#", ""))); /* color */
		gd.setCornerRadius((int)_radius); /* radius */
		gd.setStroke(0, Color.WHITE); /* stroke heigth and color */
		_view.setBackground(gd);
	}
	
	
	private void _bounce (final View _view) {
		oa_d.setTarget(_view);
		oa_d.setPropertyName("rotation");
		oa_d.setFloatValues((float)(90), (float)(0));
		oa_d.setDuration((int)(1000));
		oa_d.setInterpolator(new BounceInterpolator());
		oa_d.start();
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
