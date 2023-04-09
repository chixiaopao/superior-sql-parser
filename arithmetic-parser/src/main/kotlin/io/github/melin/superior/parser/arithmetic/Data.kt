package io.github.melin.superior.parser.arithmetic

import io.github.melin.superior.common.PrivilegeType
import io.github.melin.superior.common.Statement

data class ArithmeticData(
    val variables: java.util.HashSet<String> = HashSet(),
    val functions: java.util.HashSet<String> = HashSet()
): Statement() {
    override val privilegeType: PrivilegeType = PrivilegeType.OTHER
}