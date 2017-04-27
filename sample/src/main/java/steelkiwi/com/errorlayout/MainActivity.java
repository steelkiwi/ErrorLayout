package steelkiwi.com.errorlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.BlurTransformation;
import steelkiwi.com.library.anim.AnimationType;
import steelkiwi.com.library.view.ErrorContainer;
import steelkiwi.com.library.view.ToastView;


public class MainActivity extends AppCompatActivity {
    private ErrorContainer errorContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareBlurBackground();
        errorContainer = (ErrorContainer) findViewById(R.id.container);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText name = (EditText) findViewById(R.id.name);
        Button click = (Button) findViewById(R.id.click);

        ToastView toastView = new ToastView.Builder(this)
                .setTextSize(14)
                .setTextColor(Color.WHITE)
                .setTextGravity(Gravity.CENTER)
                .setAnimationType(AnimationType.TOP)
                .setShowDelay(2)
                .setUseActionBar(true)
                .build();
        errorContainer.setToast(toastView);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                String nameText = name.getText().toString();
                if(nameText.equals("")) {
                    errorContainer.showError(name, getString(R.string.empty_name_message));
                    return;
                }
                if(emailText.equals("")) {
                    errorContainer.showError(email, getString(R.string.empty_email_message));
                    return;
                }
                if(passwordText.equals("")){
                    errorContainer.showError(password, getString(R.string.empty_password_message));
                    return;
                }
                Toast.makeText(getApplicationContext(), "Success login", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void prepareBlurBackground(){
        ImageView blurBackgroundView = (ImageView) findViewById(R.id.ivBackground);
        Picasso.with(this).load(R.mipmap.bg).transform(new BlurTransformation(this)).into(blurBackgroundView);
    }
}
