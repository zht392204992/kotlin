/*
 * Copyright 2010-2015 JetBrains s.r.o.
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

package org.jetbrains.kotlin.di;

import com.intellij.openapi.project.Project;
import org.jetbrains.kotlin.context.GlobalContext;
import org.jetbrains.kotlin.storage.StorageManager;
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.platform.PlatformToKotlinClassMap;
import org.jetbrains.kotlin.resolve.lazy.declarations.DeclarationProviderFactory;
import org.jetbrains.kotlin.resolve.BindingTrace;
import org.jetbrains.kotlin.resolve.AdditionalCheckerProvider;
import org.jetbrains.kotlin.types.DynamicTypesSettings;
import org.jetbrains.kotlin.resolve.lazy.ResolveSession;
import org.jetbrains.kotlin.resolve.lazy.ScopeProvider;
import org.jetbrains.kotlin.context.LazyResolveToken;
import org.jetbrains.kotlin.resolve.LazyLocalClassifierAnalyzer;
import org.jetbrains.kotlin.resolve.AnnotationResolver;
import org.jetbrains.kotlin.resolve.calls.CallResolver;
import org.jetbrains.kotlin.resolve.calls.ArgumentTypeResolver;
import org.jetbrains.kotlin.types.expressions.ExpressionTypingServices;
import org.jetbrains.kotlin.types.expressions.ExpressionTypingComponents;
import org.jetbrains.kotlin.types.expressions.ControlStructureTypingUtils;
import org.jetbrains.kotlin.types.expressions.ExpressionTypingUtils;
import org.jetbrains.kotlin.types.expressions.ForLoopConventionsChecker;
import org.jetbrains.kotlin.types.reflect.ReflectionTypes;
import org.jetbrains.kotlin.resolve.calls.CallExpressionResolver;
import org.jetbrains.kotlin.resolve.DescriptorResolver;
import org.jetbrains.kotlin.resolve.DelegatedPropertyResolver;
import org.jetbrains.kotlin.resolve.TypeResolver;
import org.jetbrains.kotlin.resolve.QualifiedExpressionResolver;
import org.jetbrains.kotlin.resolve.TypeResolver.FlexibleTypeCapabilitiesProvider;
import org.jetbrains.kotlin.resolve.PartialBodyResolveProvider;
import org.jetbrains.kotlin.resolve.calls.CallCompleter;
import org.jetbrains.kotlin.resolve.calls.CandidateResolver;
import org.jetbrains.kotlin.resolve.calls.tasks.TaskPrioritizer;
import org.jetbrains.kotlin.psi.JetImportsFactory;
import org.jetbrains.kotlin.resolve.lazy.LazyDeclarationResolver;
import org.jetbrains.kotlin.resolve.lazy.DeclarationScopeProviderImpl;
import org.jetbrains.kotlin.resolve.ScriptBodyResolver;
import org.jetbrains.kotlin.resolve.lazy.ScopeProvider.AdditionalFileScopeProvider;
import org.jetbrains.annotations.NotNull;
import javax.annotation.PreDestroy;

/* This file is generated by org.jetbrains.kotlin.generators.injectors.InjectorsPackage. DO NOT EDIT! */
@SuppressWarnings("all")
public class InjectorForLazyResolve {

    private final Project project;
    private final GlobalContext globalContext;
    private final StorageManager storageManager;
    private final ModuleDescriptorImpl moduleDescriptor;
    private final KotlinBuiltIns kotlinBuiltIns;
    private final PlatformToKotlinClassMap platformToKotlinClassMap;
    private final DeclarationProviderFactory declarationProviderFactory;
    private final BindingTrace bindingTrace;
    private final AdditionalCheckerProvider additionalCheckerProvider;
    private final DynamicTypesSettings dynamicTypesSettings;
    private final ResolveSession resolveSession;
    private final ScopeProvider scopeProvider;
    private final LazyResolveToken lazyResolveToken;
    private final LazyLocalClassifierAnalyzer lazyLocalClassifierAnalyzer;
    private final AnnotationResolver annotationResolver;
    private final CallResolver callResolver;
    private final ArgumentTypeResolver argumentTypeResolver;
    private final ExpressionTypingServices expressionTypingServices;
    private final ExpressionTypingComponents expressionTypingComponents;
    private final ControlStructureTypingUtils controlStructureTypingUtils;
    private final ExpressionTypingUtils expressionTypingUtils;
    private final ForLoopConventionsChecker forLoopConventionsChecker;
    private final ReflectionTypes reflectionTypes;
    private final CallExpressionResolver callExpressionResolver;
    private final DescriptorResolver descriptorResolver;
    private final DelegatedPropertyResolver delegatedPropertyResolver;
    private final TypeResolver typeResolver;
    private final QualifiedExpressionResolver qualifiedExpressionResolver;
    private final FlexibleTypeCapabilitiesProvider flexibleTypeCapabilitiesProvider;
    private final PartialBodyResolveProvider partialBodyResolveProvider;
    private final CallCompleter callCompleter;
    private final CandidateResolver candidateResolver;
    private final TaskPrioritizer taskPrioritizer;
    private final JetImportsFactory jetImportsFactory;
    private final LazyDeclarationResolver lazyDeclarationResolver;
    private final DeclarationScopeProviderImpl declarationScopeProvider;
    private final ScriptBodyResolver scriptBodyResolver;
    private final AdditionalFileScopeProvider additionalFileScopeProvider;

    public InjectorForLazyResolve(
        @NotNull Project project,
        @NotNull GlobalContext globalContext,
        @NotNull ModuleDescriptorImpl moduleDescriptor,
        @NotNull DeclarationProviderFactory declarationProviderFactory,
        @NotNull BindingTrace bindingTrace,
        @NotNull AdditionalCheckerProvider additionalCheckerProvider,
        @NotNull DynamicTypesSettings dynamicTypesSettings
    ) {
        this.project = project;
        this.globalContext = globalContext;
        this.storageManager = globalContext.getStorageManager();
        this.moduleDescriptor = moduleDescriptor;
        this.kotlinBuiltIns = moduleDescriptor.getBuiltIns();
        this.platformToKotlinClassMap = moduleDescriptor.getPlatformToKotlinClassMap();
        this.declarationProviderFactory = declarationProviderFactory;
        this.bindingTrace = bindingTrace;
        this.additionalCheckerProvider = additionalCheckerProvider;
        this.dynamicTypesSettings = dynamicTypesSettings;
        this.resolveSession = new ResolveSession(project, globalContext, moduleDescriptor, declarationProviderFactory, bindingTrace);
        this.scopeProvider = new ScopeProvider(getResolveSession());
        this.lazyResolveToken = new LazyResolveToken();
        this.descriptorResolver = new DescriptorResolver();
        this.annotationResolver = new AnnotationResolver();
        this.qualifiedExpressionResolver = new QualifiedExpressionResolver();
        this.flexibleTypeCapabilitiesProvider = new FlexibleTypeCapabilitiesProvider();
        this.typeResolver = new TypeResolver(annotationResolver, qualifiedExpressionResolver, moduleDescriptor, flexibleTypeCapabilitiesProvider, storageManager, lazyResolveToken, dynamicTypesSettings);
        this.lazyLocalClassifierAnalyzer = new LazyLocalClassifierAnalyzer(descriptorResolver, typeResolver, annotationResolver);
        this.callResolver = new CallResolver();
        this.argumentTypeResolver = new ArgumentTypeResolver();
        this.expressionTypingComponents = new ExpressionTypingComponents();
        this.expressionTypingServices = new ExpressionTypingServices(expressionTypingComponents);
        this.controlStructureTypingUtils = new ControlStructureTypingUtils(expressionTypingServices);
        this.expressionTypingUtils = new ExpressionTypingUtils(expressionTypingServices, callResolver, kotlinBuiltIns);
        this.forLoopConventionsChecker = new ForLoopConventionsChecker();
        this.reflectionTypes = new ReflectionTypes(moduleDescriptor);
        this.callExpressionResolver = new CallExpressionResolver();
        this.delegatedPropertyResolver = new DelegatedPropertyResolver();
        this.partialBodyResolveProvider = new PartialBodyResolveProvider();
        this.candidateResolver = new CandidateResolver();
        this.callCompleter = new CallCompleter(argumentTypeResolver, candidateResolver);
        this.taskPrioritizer = new TaskPrioritizer(storageManager);
        this.jetImportsFactory = new JetImportsFactory();
        this.lazyDeclarationResolver = new LazyDeclarationResolver(globalContext, bindingTrace);
        this.declarationScopeProvider = new DeclarationScopeProviderImpl(lazyDeclarationResolver);
        this.scriptBodyResolver = new ScriptBodyResolver();
        this.additionalFileScopeProvider = new AdditionalFileScopeProvider();

        this.resolveSession.setAnnotationResolve(annotationResolver);
        this.resolveSession.setDescriptorResolver(descriptorResolver);
        this.resolveSession.setJetImportFactory(jetImportsFactory);
        this.resolveSession.setLazyDeclarationResolver(lazyDeclarationResolver);
        this.resolveSession.setQualifiedExpressionResolver(qualifiedExpressionResolver);
        this.resolveSession.setScopeProvider(scopeProvider);
        this.resolveSession.setScriptBodyResolver(scriptBodyResolver);
        this.resolveSession.setTypeResolver(typeResolver);

        scopeProvider.setAdditionalFileScopesProvider(additionalFileScopeProvider);
        scopeProvider.setDeclarationScopeProvider(declarationScopeProvider);

        annotationResolver.setCallResolver(callResolver);
        annotationResolver.setStorageManager(storageManager);
        annotationResolver.setTypeResolver(typeResolver);

        callResolver.setArgumentTypeResolver(argumentTypeResolver);
        callResolver.setCallCompleter(callCompleter);
        callResolver.setCandidateResolver(candidateResolver);
        callResolver.setExpressionTypingServices(expressionTypingServices);
        callResolver.setTaskPrioritizer(taskPrioritizer);
        callResolver.setTypeResolver(typeResolver);

        argumentTypeResolver.setBuiltIns(kotlinBuiltIns);
        argumentTypeResolver.setExpressionTypingServices(expressionTypingServices);
        argumentTypeResolver.setTypeResolver(typeResolver);

        expressionTypingServices.setAnnotationResolver(annotationResolver);
        expressionTypingServices.setBuiltIns(kotlinBuiltIns);
        expressionTypingServices.setCallExpressionResolver(callExpressionResolver);
        expressionTypingServices.setCallResolver(callResolver);
        expressionTypingServices.setDescriptorResolver(descriptorResolver);
        expressionTypingServices.setPartialBodyResolveProvider(partialBodyResolveProvider);
        expressionTypingServices.setProject(project);
        expressionTypingServices.setTypeResolver(typeResolver);

        expressionTypingComponents.setAdditionalCheckerProvider(additionalCheckerProvider);
        expressionTypingComponents.setBuiltIns(kotlinBuiltIns);
        expressionTypingComponents.setCallResolver(callResolver);
        expressionTypingComponents.setControlStructureTypingUtils(controlStructureTypingUtils);
        expressionTypingComponents.setDynamicTypesSettings(dynamicTypesSettings);
        expressionTypingComponents.setExpressionTypingServices(expressionTypingServices);
        expressionTypingComponents.setExpressionTypingUtils(expressionTypingUtils);
        expressionTypingComponents.setForLoopConventionsChecker(forLoopConventionsChecker);
        expressionTypingComponents.setGlobalContext(globalContext);
        expressionTypingComponents.setLocalClassifierAnalyzer(lazyLocalClassifierAnalyzer);
        expressionTypingComponents.setPlatformToKotlinClassMap(platformToKotlinClassMap);
        expressionTypingComponents.setReflectionTypes(reflectionTypes);

        forLoopConventionsChecker.setBuiltIns(kotlinBuiltIns);
        forLoopConventionsChecker.setExpressionTypingServices(expressionTypingServices);
        forLoopConventionsChecker.setExpressionTypingUtils(expressionTypingUtils);
        forLoopConventionsChecker.setProject(project);

        callExpressionResolver.setExpressionTypingServices(expressionTypingServices);

        descriptorResolver.setAnnotationResolver(annotationResolver);
        descriptorResolver.setBuiltIns(kotlinBuiltIns);
        descriptorResolver.setDelegatedPropertyResolver(delegatedPropertyResolver);
        descriptorResolver.setExpressionTypingServices(expressionTypingServices);
        descriptorResolver.setStorageManager(storageManager);
        descriptorResolver.setTypeResolver(typeResolver);

        delegatedPropertyResolver.setBuiltIns(kotlinBuiltIns);
        delegatedPropertyResolver.setCallResolver(callResolver);
        delegatedPropertyResolver.setExpressionTypingServices(expressionTypingServices);

        candidateResolver.setArgumentTypeResolver(argumentTypeResolver);

        jetImportsFactory.setProject(project);

        lazyDeclarationResolver.setDeclarationScopeProvider(declarationScopeProvider);
        lazyDeclarationResolver.setTopLevelDescriptorProvider(resolveSession);

        declarationScopeProvider.setFileScopeProvider(scopeProvider);

        scriptBodyResolver.setExpressionTypingServices(expressionTypingServices);

    }

    @PreDestroy
    public void destroy() {
    }

    public ResolveSession getResolveSession() {
        return this.resolveSession;
    }

}
