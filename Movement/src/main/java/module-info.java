import dk.sdu.mmmi.cbse.common.movement.MovementSPI;

module Movement {
    requires Common;
    requires CommonMovement;
    uses dk.sdu.mmmi.cbse.common.movement.MovementSPI;
    provides MovementSPI with dk.sdu.mmmi.cbse.movement.Movement;
}