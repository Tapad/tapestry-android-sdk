package com.tapad.tapestry;

/**
 * A callback that handles a {@link TapestryResponse} asynchronously.  Invoked by {@link TapestryClient}.
 */
public interface TapestryCallback {
    public void receive(TapestryResponse response);

    public static TapestryCallback DO_NOTHING = new TapestryCallback() {
        @Override
        public void receive(TapestryResponse response) {
        }
    };
}