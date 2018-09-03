package masterung.androidthai.in.th.democamera;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private ImageView photoImageView;
    private Uri uri;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Initila Image View
        photoImageView = getView().findViewById(R.id.imvPhoto);

//        Gallry Controller
        gallryController();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {
            switch (requestCode) {
                case 1:
                    uri = data.getData();

                    try {

                        Bitmap bitmap = BitmapFactory
                                .decodeStream(getActivity()
                                        .getContentResolver().openInputStream(uri));
                        Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap,
                                800, 600, false);
                        photoImageView.setImageBitmap(bitmap1);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }   // if

    }   // onActivityResult

    private void gallryController() {
        ImageView imageView = getView().findViewById(R.id.imvGallry);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

}
