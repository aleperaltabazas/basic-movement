package com.basic.movement.player;

public enum Direction {
    North {
        @Override
        public float getFacingX() {
            return 0;
        }

        @Override
        public float getFacingY() {
            return TILE_HEIGHT;
        }
    },
    South {
        @Override
        public float getFacingX() {
            return 0;
        }

        @Override
        public float getFacingY() {
            return -TILE_HEIGHT;
        }
    },
    West {
        @Override
        public float getFacingX() {
            return -TILE_WIDTH;
        }

        @Override
        public float getFacingY() {
            return 0;
        }
    },
    East {
        @Override
        public float getFacingX() {
            return TILE_WIDTH;
        }

        @Override
        public float getFacingY() {
            return 0;
        }
    };

    static final float TILE_WIDTH = 16;
    static final float TILE_HEIGHT = 16;

    public abstract float getFacingX();

    public abstract float getFacingY();
}
