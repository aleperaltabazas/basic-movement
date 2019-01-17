package com.basic.movement.utils;

import com.badlogic.gdx.physics.box2d.*;
import com.basic.movement.world.InteractiveTile;

import java.util.Objects;

public class OverworldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        if (actOnDirection(contact, "north"))
            return;
        else if (actOnDirection(contact, "south"))
            return;
        else if (actOnDirection(contact, "east"))
            return;
        actOnDirection(contact, "west");

    }

    private boolean actOnDirection(Contact contact, String direction) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (Objects.equals(fixtureA.getUserData(), direction) || Objects.equals(fixtureB.getUserData(), direction)) {
            Fixture head = Objects.equals(fixtureA.getUserData(), direction) ? fixtureA : fixtureB;
            Fixture object = head == fixtureA ? fixtureB : fixtureA;

            if ((object.getUserData() != null) && (object.getUserData() instanceof InteractiveTile)) {
                System.out.println(direction);
            } else {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
