package com.smp.aplikacjasp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import android.widget.Toast;
import android.content.Intent;

public class ThirdFragment extends Fragment {
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.third_layout, container, false);
        return myView;
    }


    public class Fragment extends YouTubeBaseActivity
            implements YouTubePlayer.OnInitializedListener {
        public static final String DEVELOPER_KEY = "AIzaSyBOHHFkQqnQQIIrsU-WAJTZ0i6YRh6e_ag";
        private static final String VIDEO_ID = "3LiubyYpEUk";
        private static final int RECOVERY_DIALOG_REQUEST = 1;
        YouTubePlayerFragment myYouTubePlayerFragment;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.third_layout);
            myYouTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager()
                    .findFragmentById(R.id.youtubeplayerfragment);
            myYouTubePlayerFragment.initialize(DEVELOPER_KEY, this);
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider,
                                            YouTubeInitializationResult errorReason) {
            if (errorReason.isUserRecoverableError()) {
                errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
            } else {
                String errorMessage = String.format(
                        "There was an error initializing the YouTubePlayer (%1$s)",
                        errorReason.toString());
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onInitializationSuccess(Provider provider, YouTubePlayer player,
                                            boolean wasRestored) {
            if (!wasRestored) {
                player.cueVideo(VIDEO_ID);
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == RECOVERY_DIALOG_REQUEST) {
// Retry initialization if user performed a recovery action
                getYouTubePlayerProvider().initialize(DEVELOPER_KEY, this);
            }
        }

        protected YouTubePlayer.Provider getYouTubePlayerProvider() {
            return (YouTubePlayerView) findViewById(R.id.youtubeplayerfragment);
        }
    }

}