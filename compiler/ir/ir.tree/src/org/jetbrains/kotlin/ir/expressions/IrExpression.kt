/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.ir.expressions

import org.jetbrains.kotlin.ir.IrElementBase
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrAttributeContainer
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.visitors.IrElementTransformer
import org.jetbrains.kotlin.ir.visitors.IrElementVisitor

abstract class IrExpression : IrElementBase(), IrStatement, IrVarargElement, IrAttributeContainer {
    @Suppress("LeakingThis")
    override var attributeOwnerId: IrAttributeContainer = this

    abstract var type: IrType

    override fun <D> transform(transformer: IrElementTransformer<D>, data: D): IrExpression =
        accept(transformer, data) as IrExpression

    override fun <D> acceptChildren(visitor: IrElementVisitor<Unit, D>, data: D) {
        // No children by default
    }

    override fun <D> transformChildren(transformer: IrElementTransformer<D>, data: D) {
        // No children by default
    }
}

interface IrExpressionWithCopy {
    fun copy(): IrExpression
}
