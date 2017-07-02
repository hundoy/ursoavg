package com.urso.avg.test.ashley;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;

/**
 * Created by zohar on 2016/12/4.
 */
public class BulletComponent implements Component {
    public MotionState motionState;
    public btRigidBody.btRigidBodyConstructionInfo bodyInfo;
    public btCollisionObject body;
}
