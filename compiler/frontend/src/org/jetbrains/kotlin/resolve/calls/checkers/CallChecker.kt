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

package org.jetbrains.kotlin.resolve.calls.checkers

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.config.LanguageFeatureSettings
import org.jetbrains.kotlin.descriptors.PropertyAccessorDescriptor
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.context.ResolutionContext
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfo
import org.jetbrains.kotlin.resolve.scopes.LexicalScope
import org.jetbrains.kotlin.types.DeferredType
import org.jetbrains.kotlin.types.KotlinType

interface CallChecker {
    /**
     * Note that [reportOn] should only be used as a target element for diagnostics reported by checkers.
     * Logic of the checker should not depend on what element is the target of the diagnostic!
     */
    fun check(resolvedCall: ResolvedCall<*>, reportOn: PsiElement, context: CallCheckerContext)

    /**
     * This method is needed because for the simple assignment expression like "a = b" there is no resolved call that points to a's setter
     * (the only resolved call is the one pointing to the property 'a' itself). So [check] which takes [ResolvedCall] is not applicable here
     *
     * TODO: construct a special ResolvedCall instead and call [check]
     */
    fun checkPropertyCall(descriptor: PropertyAccessorDescriptor, reportOn: PsiElement, context: CallCheckerContext) {
    }
}

class CallCheckerContext(
        val trace: BindingTrace,
        val scope: LexicalScope,
        val languageFeatureSettings: LanguageFeatureSettings,
        val dataFlowInfo: DataFlowInfo,
        val isAnnotationContext: Boolean
) {
    constructor(c: ResolutionContext<*>, languageFeatureSettings: LanguageFeatureSettings) : this(
            c.trace, c.scope, languageFeatureSettings, c.dataFlowInfo, c.isAnnotationContext
    )
}

// Use this utility to avoid premature computation of deferred return type of a resolved callable descriptor.
// Computing it in CallChecker#check is not feasible since it would trigger "type checking has run into a recursive problem" errors.
// Receiver parameter is present to emphasize that this function should ideally be only used from call checkers.
@Suppress("unused")
fun CallChecker.isComputingDeferredType(type: KotlinType) =
        type is DeferredType && type.isComputing
